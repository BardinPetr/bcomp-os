package ru.bardinpetr.itmo.lab_4.abilities.errors;

import ru.bardinpetr.itmo.lab_4.abilities.Ability;

public class NotPureAbilityException extends RuntimeException {
    private final Ability ability;

    public NotPureAbilityException(Ability ability) {
        super("This instance of Ability %s is not pure, therefore should not be referenced by name"
                .formatted(ability.getClass().getName()));
        this.ability = ability;
    }
}
