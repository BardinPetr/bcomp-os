import pokebase as pb
import re
import subprocess
import tqdm
from functools import reduce
from jinja2 import Environment, select_autoescape, FileSystemLoader
from os import mkdir
from typing import Optional

# pokemons = {}
#
# for i in range(6):
#     name = input().strip()
#     [input() for _ in range(2)]
#     atts = []
#     while (att := input()) != '':
#         atts.append(att)
#     pokemons[name] = atts
#
# print(pokemons)
# exit(0)


INHERITED_PARAMS = ("attacks", "types")
ALLIE_TARGETS = ['selected-pokemon-me-first', 'users-field', 'ally', 'user-or-ally', 'user', 'user-and-allies',
                 'all-allies', 'entire-field', 'all-pokemon']
FOE_TARGETS = ['opponents-field', 'random-opponent', 'all-other-pokemon', 'selected-pokemon', 'all-opponents',
               'entire-field', 'all-pokemon']

POKEMONS = {
    'Celebi': ['Psychic', 'Calm Mind', 'Ancient Power', 'Double Team'],
    'Eevee': ['Rest', 'Tackle', 'Double Team'],
    'Leafeon': ['Rest', 'Tackle', 'Double Team', 'Energy Ball'],
    'Grubbin': ['Rest', 'Vice Grip'],
    'Charjabug': ['Rest', 'Vice Grip', 'Discharge'],
    'Vikavolt': ['Rest', 'Vice Grip', 'Discharge', 'Poison Jab']
}

ATTACK_TYPES = {}


def class_format(x):
    x = re.split(r'[\s\-_]+', x)
    return reduce(lambda acc, i: acc + i.capitalize(), x, "")


def dash_format(x):
    x = re.split(r'[\s\-_]+', x)
    return '-'.join(i.lower() for i in x)


def upper_format(x):
    x = re.split(r'[\s\-_]+', x)
    return '_'.join(i.upper() for i in x)


def type_format(x):
    return f"Type.{upper_format(x)}"


def ailment_translation(x):
    TR = {
        "paralysis": "paralyze",
        "confusion": "confuse",
        # "infatuation": "",
        # "trap": "",
        # "nightmare": "",
        # "torment": "",
        # "disable": "",
        # "yawn": "",
        # "heal-block": "",
        # "no-type-immunity": "",
        # "leech-seed": "",
        # "embargo": "",
        # "perish-song": "",
        # "ingrain": ""
    }
    res = TR.get(x, x)
    return None if res == 'none' else res


env = Environment(
    loader=FileSystemLoader("templates"),
    autoescape=select_autoescape()
)
env.filters['dash_format'] = dash_format
env.filters['class_format'] = class_format
env.filters['upper_format'] = upper_format
env.filters['type_format'] = type_format


def get_ancestor(name) -> Optional[str]:
    anc = pb.APIResource('pokemon-species', name.lower()).evolves_from_species
    species = None if anc is None else anc.name
    return class_format(species) if species is not None else None


def load_pokemon(src):
    name, attacks = src
    api_name = name.lower()

    pk = pb.APIResource('pokemon', api_name)
    anc = get_ancestor(api_name)

    stats = {upper_format(i.stat.name): i.base_stat for i in pk.stats}
    types = {i.type.name for i in pk.types}

    return {
        "name": class_format(name),
        "attacks": set(attacks),
        "stats": stats,
        "types": types,
        "ancestor": anc,
        "child": []
    }


def gen_pokemon(pks, name):
    cur = pks[name].copy()
    anc = pks.get(cur["ancestor"])
    if anc is not None:
        inh_params = {i: (cur[i] - anc[i])
                      for i in INHERITED_PARAMS
                      if anc[i].issubset(cur[i])}
        cur.update(inh_params)
    cur["attacks"] = {(ATTACK_TYPES[i], class_format(i)) for i in cur["attacks"]}
    return env \
        .get_template('Pokemon.java.jinja2') \
        .render(**cur)


def gen_attack(name):
    global ATTACK_TYPES

    move = pb.APIResource('move', '-'.join(name.lower().split()))
    damage_class = move.damage_class.name
    ATTACK_TYPES[name] = damage_class
    texts = [(i.effect, i.short_effect)
             for i in move.effect_entries
             if i.language.name == "en"]
    texts = [i.replace("\n", " ")
             .replace("$effect_chance", str(move.effect_chance))
             for i in texts[0]]
    att_superclass = f"{class_format(damage_class)}Move"
    ailment = ailment_translation(move.meta.ailment.name)
    stats = {i.stat.name: i.change for i in move.stat_changes}

    effect = {
        "apply_self": move.target.name in ALLIE_TARGETS,
        "apply_opp": move.target.name in FOE_TARGETS,
        "is_ailment": ailment is not None,
        "ailment": ailment,
        "ailment_chance": move.meta.ailment_chance,
        "is_stats": len(stats) > 0,
        "stats_changes": stats,
        "stats_chance": move.effect_chance if move.effect_chance is not None else 100
    }

    render = env.get_template(f'Move.java.jinja2') \
        .render(name=name,
                m=move,
                att_superclass=att_superclass,
                effect=effect,
                full_desc=texts[0],
                desc=texts[1])
    return render, f"{damage_class.lower()}/{class_format(name)}.java"


def prettify(path):
    subprocess.call(["prettier", "--tab-width", "4", "--write", f'{path}/**/*.java'])


if __name__ == "__main__":
    main_dir = "../app/src/main/java/ru/bardinpetr/itmo/lab_2"
    print("Downloading Pokemon")
    attacks_all = reduce(lambda acc, i: acc.union(set(i)), POKEMONS.values(), set())
    POKEMONS = {class_format(i[0]): load_pokemon(i) for i in tqdm.tqdm(POKEMONS.items())}

    print("\nGenerating attacks")
    for i in tqdm.tqdm(attacks_all):
        content, fname = gen_attack(i)
        path = f'{main_dir}/moves/{fname}'
        try:
            mkdir(path[:path.rfind('/')])
        except FileExistsError:
            pass
        try:
            with open(path, 'r') as f:
                if "#prevent_autogen" in f.read():
                    continue
        except FileNotFoundError:
            pass
        with open(path, 'w') as f:
            f.write(content)

    print("\nGenerating Pokemon")
    for pk_name in tqdm.tqdm(POKEMONS.keys()):
        with open(f'{main_dir}/pokemons/{pk_name}.java', 'w') as f:
            f.write(gen_pokemon(POKEMONS, pk_name))

    print("\nFormatting files")
    prettify(main_dir)
