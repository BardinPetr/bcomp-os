package ru.bardinpetr.itmo.lab_4.story.actions;

import ru.bardinpetr.itmo.lab_4.realitylib.abilities.Ability;
import ru.bardinpetr.itmo.lab_4.realitylib.abilities.interfaces.Describable;

import java.util.Objects;

public class LikeAction extends Ability {

    public static final String TYPE = "like";
    private final Describable describable;
    private boolean isLike;

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

    public Describable getDescribable() {
        return describable;
    }

    @Override
    public String getVerb() {
        return "%sлюбит".formatted(isLike ? "" : "не ");
    }

    @Override
    public String getDescription() {
        return describable.describe();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        LikeAction that = (LikeAction) o;

        if (isLike != that.isLike) return false;
        return Objects.equals(describable, that.describable);
    }


    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), describable, isLike);
    }

    @Override
    public String toString() {
        return "LikeAction{describable=%s, isLike=%s} %s".formatted(describable, isLike, super.toString());
    }
}
