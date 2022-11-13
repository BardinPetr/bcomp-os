package ru.bardinpetr.itmo.lab_3.abilities.actions;

import ru.bardinpetr.itmo.lab_3.abilities.Ability;

public class LookLikeAction extends Ability {
    public static final String TYPE = "выглядеть";

    private final String target;

    public LookLikeAction(String target) {
        super(TYPE);
        this.target = target;
    }

    @Override
    public String perform() {
        return "выглядит как %s".formatted(target);
    }
}
