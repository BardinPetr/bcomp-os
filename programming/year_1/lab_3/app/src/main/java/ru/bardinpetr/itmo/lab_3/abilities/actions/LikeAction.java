package ru.bardinpetr.itmo.lab_3.abilities.actions;

import ru.bardinpetr.itmo.lab_3.abilities.Ability;
import ru.bardinpetr.itmo.lab_3.abilities.interfaces.Describable;

public class LikeAction extends Ability {
    public static final String TYPE = "любить";

    private final Describable describable;
    private final boolean isLike;

    public LikeAction(Describable describable) {
        super(TYPE);
        this.describable = describable;
        this.isLike = true;
    }

    public LikeAction(Describable describable, boolean isLike) {
        super(TYPE);
        this.describable = describable;
        this.isLike = isLike;
    }

    @Override
    public String perform() {
        return "%sлюбит %s".formatted(isLike ? "" : "не ", describable.describe());
    }
}
