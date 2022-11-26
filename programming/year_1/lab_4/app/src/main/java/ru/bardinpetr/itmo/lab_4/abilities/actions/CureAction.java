package ru.bardinpetr.itmo.lab_4.abilities.actions;

import ru.bardinpetr.itmo.lab_4.abilities.Ability;
import ru.bardinpetr.itmo.lab_4.properties.models.Illness;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        CureAction that = (CureAction) o;

        return cureFrom.equals(that.cureFrom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cureFrom);
    }

    @Override
    public String toString() {
        return "CureAction{cureFrom=%s} %s".formatted(cureFrom, super.toString());
    }
}
