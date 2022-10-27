package ru.bardinpetr.itmo.lab_3.human;


import ru.bardinpetr.itmo.lab_3.abilities.Ability;
import ru.bardinpetr.itmo.lab_3.abilities.interfaces.Able;
import ru.bardinpetr.itmo.lab_3.abilities.interfaces.Describable;
import ru.bardinpetr.itmo.lab_3.properties.Modifier;
import ru.bardinpetr.itmo.lab_3.properties.interfaces.Modifiable;

import java.util.ArrayList;
import java.util.List;

public class LivingHuman extends BaseHuman implements Able, Modifiable {
    private final List<Ability> abilities = new ArrayList<>();
    private final List<Modifier> properties = new ArrayList<>();

    public LivingHuman(String name, String patronymic, String surname) {
        super(name, patronymic, surname);
    }

    public LivingHuman(String name) {
        super(name);
    }

    @Override
    public void addAbility(Ability ability) {
        abilities.add(ability);
    }

    @Override
    public List<Ability> getAbilities() {
        return abilities;
    }

    @Override
    public String describe() {
        StringBuilder sb = new StringBuilder();

        sb.append("%s умеет: \n".formatted(getFullName()));
        for (Describable i : getAbilities())
            sb.append("%s\n".formatted(i.describe()));

        sb.append("%s обладает свойствами: \n".formatted(getName()));
        sb.append(describeMods());
        sb.append("\n");

        return sb.toString();
    }

    @Override
    public void applyModifier(Modifier mod) {
        properties.add(mod);
    }

    @Override
    public List<Modifier> getModifiers() {
        return properties;
    }
}
