package ru.bardinpetr.itmo.lab_3.abilities.actions;

import ru.bardinpetr.itmo.lab_3.abilities.Ability;
import ru.bardinpetr.itmo.lab_3.properties.models.Illness;

import java.util.ArrayList;
import java.util.List;

public class CureAction extends Ability {
    public static final String TYPE = "cure";

    private final List<Illness> cureFrom = new ArrayList<>();

    public CureAction() {
        super(TYPE);
    }

    public void addCuredIllness(Illness illness) {
        cureFrom.add(illness);
    }

    @Override
    public String getVerb() {
        return "лечит";
    }

    @Override
    public String getDescription() {
        StringBuilder stringBuilder = new StringBuilder(" от ");
        for (int i = 0; i < cureFrom.size(); i++)
            stringBuilder.append("%s, ".formatted(cureFrom.get(i).toString()));
        return stringBuilder.toString();
    }
}
