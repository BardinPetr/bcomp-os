package ru.bardinpetr.itmo.lab_3.abilities.actions;

import ru.bardinpetr.itmo.lab_3.abilities.Ability;
import ru.bardinpetr.itmo.lab_3.things.wear.Clothing;

import java.util.ArrayList;
import java.util.List;

public class WearAction extends Ability {
    public static final String TYPE = "wear";

    private final List<Clothing> wearing = new ArrayList<>();

    public WearAction() {
        super(TYPE);
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
}
