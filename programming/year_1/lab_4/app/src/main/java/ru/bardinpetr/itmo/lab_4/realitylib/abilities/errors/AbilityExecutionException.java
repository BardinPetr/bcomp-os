package ru.bardinpetr.itmo.lab_4.realitylib.abilities.errors;

import ru.bardinpetr.itmo.lab_4.realitylib.things.PhysicalObject;

public class AbilityExecutionException extends Exception {

    private final Class ability;
    private final PhysicalObject executor;
    private final Exception internal;

    public AbilityExecutionException(Class ability, PhysicalObject executor, Exception internal) {
        super("Failed to do action %s by %s because %s".formatted(
                ability.getName(),
                executor.getPhysicalObjectName(),
                internal.toString()));
        this.ability = ability;
        this.executor = executor;
        this.internal = internal;
    }

    public Class getAbility() {
        return ability;
    }

    public PhysicalObject getExecutor() {
        return executor;
    }

    public Exception getInternal() {
        return internal;
    }
}
