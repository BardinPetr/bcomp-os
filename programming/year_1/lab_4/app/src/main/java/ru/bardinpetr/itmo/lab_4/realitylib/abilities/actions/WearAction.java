package ru.bardinpetr.itmo.lab_4.realitylib.abilities.actions;

import ru.bardinpetr.itmo.lab_4.realitylib.abilities.Ability;
import ru.bardinpetr.itmo.lab_4.realitylib.things.wear.Clothing;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WearAction extends Ability implements Cloneable {
    private List<Clothing> wearing;

    public WearAction() {
        wearing = new ArrayList<>();
    }

    public WearAction(List<Clothing> wearing) {
        this.wearing = wearing;
    }

    public void setWearing(List<Clothing> wearing) {
        this.wearing = wearing;
    }

    public void putOn(Clothing clothing) {
        wearing.add(clothing);
    }

    @Override
    public String getVerb() {
        return "носить";
    }

    @Override
    public String getDescription() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < wearing.size(); i++)
            stringBuilder.append("%s, ".formatted(wearing.get(i).describe()));
        return stringBuilder.toString();
    }

    public List<Clothing> getWearing() {
        return wearing;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        WearAction that = (WearAction) o;

        return wearing.equals(that.wearing);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), wearing);
    }

    @Override
    public String toString() {
        return "WearAction{wearing=%s} %s".formatted(wearing, super.toString());
    }

    @Override
    public Ability clone() {
        WearAction clone = (WearAction) super.clone();
        clone.setWearing(List.copyOf(this.wearing));
        return clone;
    }
}
