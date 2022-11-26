package ru.bardinpetr.itmo.lab_4.creatures.humans;

import ru.bardinpetr.itmo.lab_4.abilities.Ability;
import ru.bardinpetr.itmo.lab_4.abilities.interfaces.Describable;
import ru.bardinpetr.itmo.lab_4.creatures.humans.interfaces.IPerforming;
import ru.bardinpetr.itmo.lab_4.creatures.humans.interfaces.Scriptable;
import ru.bardinpetr.itmo.lab_4.scenarios.Scenario;
import ru.bardinpetr.itmo.lab_4.things.PhysicalObject;
import ru.bardinpetr.itmo.lab_4.things.place.Place;

import java.util.*;

public class HumanGroup extends PhysicalObject implements Describable, IPerforming, Scriptable {

    private final Map<String, Ability> namedAbilities = new HashMap<>();
    private final Map<Class, Ability> typedAbilities = new HashMap<>();

    private final List<Human> group = new ArrayList<>();
    private final String name;
    private Scenario globalScenario = null;

    public HumanGroup(String name) {
        this.name = name;
    }

    public List<Human> getGroup() {
        return group;
    }

    public void add(Human human) {
        group.add(human);
    }

    @Override
    public String describe() {
        StringBuilder sb = new StringBuilder();

        sb.append("Группа %s:\n".formatted(getName()));

        var abilities = getAbilities();
        if (abilities.size() > 0) {
            sb.append("- Групповые возможности:\n");
            for (int i = 0; i < abilities.size(); i++)
                sb.append("%s;  ".formatted(abilities.get(i).describe()));
        }

        sb.append("\n- Групповой сценарий:\n%s\n".formatted(getScenario()));

        sb.append("- Содержимое группы:\n");
        for (int i = 0; i < group.size(); i++)
            sb.append("-- %s\n".formatted(group.get(i).describe()));

        return sb.toString();
    }

    @Override
    public String getPhysicalObjectName() {
        return name;
    }

    public void live(Place place) {
        for (int i = 0; i < group.size(); i++) {
            group.get(i).live(place);
        }
    }

    public Human getByName(String name) {
        for (int i = 0; i < group.size(); i++)
            if (group.get(i).getName().equals(name)) return group.get(i);
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
    public Map<Class, Ability> getPureAbilities() {
        return typedAbilities;
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

        if (!namedAbilities.equals(that.namedAbilities)) return false;
        if (!typedAbilities.equals(that.typedAbilities)) return false;
        if (!group.equals(that.group)) return false;
        if (!Objects.equals(name, that.name)) return false;
        return Objects.equals(globalScenario, that.globalScenario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), namedAbilities, typedAbilities, group, name, globalScenario);
    }

    @Override
    public String toString() {
        return "HumanGroup{group=%s, name='%s', globalScenario=%s} %s".formatted(group, name, globalScenario, super.toString());
    }
}
