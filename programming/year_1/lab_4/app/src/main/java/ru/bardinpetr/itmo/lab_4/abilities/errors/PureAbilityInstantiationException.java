package ru.bardinpetr.itmo.lab_4.abilities.errors;

public class PureAbilityInstantiationException extends RuntimeException {
    private final Class abilityClass;

    private final Exception originalException;

    public PureAbilityInstantiationException(Class abilityClass, Exception originalException) {
        super("Failed to instantiate an Ability of \"%s\" class".formatted(abilityClass.getName()));
        this.abilityClass = abilityClass;
        this.originalException = originalException;
    }

    public Class getAbilityClass() {
        return abilityClass;
    }

    public Exception getOriginalException() {
        return originalException;
    }
}
