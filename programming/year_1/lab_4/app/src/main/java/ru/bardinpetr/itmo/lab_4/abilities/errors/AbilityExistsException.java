package ru.bardinpetr.itmo.lab_4.abilities.errors;

import ru.bardinpetr.itmo.lab_4.abilities.Ability;

public class AbilityExistsException extends RuntimeException {
    private final Ability conflictingAbility;

    public AbilityExistsException(Ability conflictingAbility) {
        super(
                "Cannot add ability to object because it already have an ability named \"%s\" of class %s"
                        .formatted(
                                conflictingAbility.getAbilityName(),
                                conflictingAbility.getAbilityType())
        );
        this.conflictingAbility = conflictingAbility;
    }

    public Ability getConflictingAbility() {
        return conflictingAbility;
    }
}
