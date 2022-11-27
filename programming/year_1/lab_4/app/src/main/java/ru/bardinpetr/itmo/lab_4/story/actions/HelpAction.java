package ru.bardinpetr.itmo.lab_4.story.actions;

import ru.bardinpetr.itmo.lab_4.realitylib.abilities.Ability;
import ru.bardinpetr.itmo.lab_4.realitylib.creatures.humans.Human;

import java.util.Objects;

public class HelpAction extends Ability {
    public static final String TYPE = "помогать";

    private Human master;

    public HelpAction() {
        super(TYPE);
    }

    public Human getMaster() {
        return master;
    }

    public void setMaster(Human master) {
        this.master = master;
    }

    @Override
    public String getVerb() {
        return "помогать";
    }

    @Override
    public String getDescription() {
        return master.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        HelpAction that = (HelpAction) o;

        return Objects.equals(master, that.master);
    }


    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), master);
    }

    @Override
    public String toString() {
        return "HelpAction{master=%s} %s".formatted(master, super.toString());
    }
}
