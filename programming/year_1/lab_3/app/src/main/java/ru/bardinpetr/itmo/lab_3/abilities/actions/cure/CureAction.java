package ru.bardinpetr.itmo.lab_3.abilities.actions.cure;

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
    public String perform() {
        StringBuilder stringBuilder = new StringBuilder("лечит от ");
        for (int i = 0; i < cureFrom.size(); i++)
            stringBuilder.append("%s, ".formatted(cureFrom.get(i).name()));
        return stringBuilder.toString();
    }
}
