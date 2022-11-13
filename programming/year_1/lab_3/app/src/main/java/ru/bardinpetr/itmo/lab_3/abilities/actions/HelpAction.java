package ru.bardinpetr.itmo.lab_3.abilities.actions;

import ru.bardinpetr.itmo.lab_3.abilities.Ability;
import ru.bardinpetr.itmo.lab_3.creatures.humans.Human;

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
}
