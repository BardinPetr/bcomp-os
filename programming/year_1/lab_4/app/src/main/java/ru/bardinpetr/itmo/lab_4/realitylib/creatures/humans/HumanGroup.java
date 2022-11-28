package ru.bardinpetr.itmo.lab_4.realitylib.creatures.humans;

import ru.bardinpetr.itmo.lab_4.realitylib.abilities.Ability;
import ru.bardinpetr.itmo.lab_4.realitylib.abilities.errors.AbilityExistsException;
import ru.bardinpetr.itmo.lab_4.realitylib.abilities.interfaces.Describable;
import ru.bardinpetr.itmo.lab_4.realitylib.creatures.humans.interfaces.ICommonHumanAbilities;
import ru.bardinpetr.itmo.lab_4.realitylib.creatures.humans.interfaces.IPerforming;
import ru.bardinpetr.itmo.lab_4.realitylib.properties.interfaces.IModifier;
import ru.bardinpetr.itmo.lab_4.realitylib.scenarios.Scenario;
import ru.bardinpetr.itmo.lab_4.realitylib.scenarios.ScenarioActionFactory;
import ru.bardinpetr.itmo.lab_4.realitylib.scenarios.interfaces.IAbilityConfigurationRunnable;
import ru.bardinpetr.itmo.lab_4.realitylib.scenarios.interfaces.IScenarioAction;
import ru.bardinpetr.itmo.lab_4.realitylib.scenarios.interfaces.Scriptable;
import ru.bardinpetr.itmo.lab_4.realitylib.things.PhysicalObject;
import ru.bardinpetr.itmo.lab_4.realitylib.things.place.Place;
import ru.bardinpetr.itmo.lab_4.realitylib.things.wear.Clothing;
import ru.bardinpetr.itmo.lab_4.realitylib.abilities.actions.LiveAction;
import ru.bardinpetr.itmo.lab_4.realitylib.abilities.actions.WearAction;

import java.util.*;

public class HumanGroup extends PhysicalObject implements ICommonHumanAbilities, Describable, IPerforming, Scriptable {

    private final Map<String, Ability> namedAbilities = new HashMap<>();
    private final Set<Class> pureAbilities = new HashSet<>();

    private final List<Human> group = new ArrayList<>();
    private final String name;
    private boolean applied = false;
    private Scenario globalScenario = null;


    public HumanGroup(String name) {
        this.name = name;
    }

    public List<Human> getGroup() {
        return group;
    }

    public void add(Human human) {
        if (!applied)
            group.add(human);
    }

    public void apply() {
        applied = true;
        for (Human human : group) {
            for (Class ability : getPureAbilities()) {
                try {
                    human.addAbility(ability);
                } catch (AbilityExistsException ignored) {
                }
            }
            for (String ability : getModifiedAbilities().keySet()) {
                try {
                    human.overrideAbility(ability, getAbilityByName(ability));
                } catch (AbilityExistsException ignored) {
                }
            }
            for (IModifier mod : getModifiers()) {
                try {
                    human.setModifier(mod.getClass(), mod);
                } catch (AbilityExistsException ignored) {
                }
            }
        }
    }

    @Override
    public String describe() {
        StringBuilder sb = new StringBuilder();

        sb.append("Группа %s:\n".formatted(getName()));

        sb.append("* представители:\n");
        for (Human human : group)
            sb.append("%s, ".formatted(human.getFullName()));

        sb.append("\n* групповые способности: \n");
        for (var i : getPureAbilities())
            sb.append("%s, ".formatted(i.getSimpleName()));

        sb.append("\n* групповые действия: \n");
        for (var i : getAbilities())
            sb.append("-* %s\n".formatted(i.describe()));

        return sb.toString();
    }

    @Override
    public IScenarioAction perform(Ability ability, IAbilityConfigurationRunnable conf) {
        return ScenarioActionFactory.getInstance().newScenarioAction(
                this, ability.clone(), conf
        );
    }

    @Override
    public String getPhysicalObjectName() {
        return name;
    }

    public void live(Place place) {
        addAbility("live", new LiveAction(place));
    }

    @Override
    public void wear(Clothing thing) {
        ((WearAction) getAbility(WearAction.class)).putOn(thing);
    }

    @Override
    public void have(PhysicalObject thing) {
    }

    public Human getByName(String name) {
        for (Human human : group) if (human.getName().equals(name)) return human;
        return null;
    }

    public String getName() {
        return name;
    }


    @Override
    public Map<String, Ability> getModifiedAbilities() {
        return namedAbilities;
    }

    @Override
    public Set<Class> getPureAbilities() {
        return pureAbilities;
    }

    @Override
    public String getScenario() {
        return (globalScenario == null ? "no global scenario" : globalScenario.print());
    }

    @Override
    public void addScenario(Scenario scenario) {
        globalScenario = scenario;
        for (int i = 0; i < group.size(); i++)
            group.get(i).addScenario(globalScenario);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        HumanGroup that = (HumanGroup) o;

        if (!getAbilities().equals(that.getAbilities())) return false;
        if (!group.equals(that.group)) return false;
        if (!Objects.equals(name, that.name)) return false;
        return Objects.equals(globalScenario, that.globalScenario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getAbilities(), group, name, globalScenario);
    }

    @Override
    public String toString() {
        return "HumanGroup{group=%s, name='%s', globalScenario=%s} %s".formatted(group, name, globalScenario, super.toString());
    }
}
