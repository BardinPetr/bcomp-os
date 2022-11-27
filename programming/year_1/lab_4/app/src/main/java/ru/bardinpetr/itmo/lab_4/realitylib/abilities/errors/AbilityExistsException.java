package ru.bardinpetr.itmo.lab_4.realitylib.abilities.errors;

import ru.bardinpetr.itmo.lab_4.realitylib.abilities.Ability;

public class AbilityExistsException extends RuntimeException {
    private final Ability conflictingAbility;
    private final Class conflictingClass;
    private final String conflictingName;

    public AbilityExistsException(String name, Ability conflictingAbility) {
        super(
                "Cannot add ability to object because it already have an ability named \"%s\" of class %s"
                        .formatted(name, conflictingAbility.getClass().getName())
        );
        this.conflictingClass = conflictingAbility.getClass();
        this.conflictingName = name;
        this.conflictingAbility = conflictingAbility;
    }

    public AbilityExistsException(Class abilityClass) {
        super(
                "Cannot add ability to object because it already have an ability of class %s"
                        .formatted(abilityClass.getName())
        );
        this.conflictingAbility = null;
        this.conflictingClass = abilityClass;
        this.conflictingName = abilityClass.getName();
    }

    public Class getConflictingClass() {
        return conflictingClass;
    }

    public String getConflictingName() {
        return conflictingName;
    }

    public Ability getConflictingAbility() {
        return conflictingAbility;
    }
}
