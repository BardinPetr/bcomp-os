package ru.bardinpetr.itmo.lab_3.abilities.actions;

import ru.bardinpetr.itmo.lab_3.abilities.Ability;
import ru.bardinpetr.itmo.lab_3.abilities.interfaces.Describable;

public class LikeAction extends Ability {
    public static final String TYPE = "любить";

    private final Describable describable;

    public LikeAction(Describable describable) {
        super(TYPE);
        this.describable = describable;
    }

    @Override
    public String perform() {
        return "любит %s".formatted(describable.describe());
    }
}
