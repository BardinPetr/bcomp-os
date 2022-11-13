package ru.bardinpetr.itmo.lab_3.abilities.actions;

import ru.bardinpetr.itmo.lab_3.abilities.Ability;
import ru.bardinpetr.itmo.lab_3.properties.models.QuarrelState;

public class QuarrelAction extends Ability {
    public static final String TYPE = "ссориться";

    private final QuarrelState state;

    public QuarrelAction(QuarrelState state) {
        super(TYPE);
        this.state = state;
    }

    @Override
    public String getVerb() {
        return (state == QuarrelState.IN_QUARREL ? "ссориться" : "мириться");
    }

    @Override
    protected String getObjectPreposition() {
        return "с";
    }
}
