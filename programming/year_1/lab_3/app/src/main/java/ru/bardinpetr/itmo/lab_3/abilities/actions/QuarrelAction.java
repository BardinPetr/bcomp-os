package ru.bardinpetr.itmo.lab_3.abilities.actions;

import ru.bardinpetr.itmo.lab_3.abilities.Ability;
import ru.bardinpetr.itmo.lab_3.properties.models.QuarrelState;
import ru.bardinpetr.itmo.lab_3.things.PhysicalObject;
import ru.bardinpetr.itmo.lab_3.things.Tool;

public class QuarrelAction extends Ability {
    public static final String TYPE = "ссориться";

    private final QuarrelState state;

    public QuarrelAction(QuarrelState state) {
        super(TYPE);
        this.state = state;
    }

    @Override
    public String perform() {
        return (state == QuarrelState.IN_QUARREL ? "ссориться" : "мириться");
    }

    @Override
    public String performWithOn(Tool tool, PhysicalObject object) {
        return "%s с %s".formatted(perform(), object.getPhysicalObjectName());
    }
}
