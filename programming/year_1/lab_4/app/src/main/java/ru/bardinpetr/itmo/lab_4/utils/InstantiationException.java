package ru.bardinpetr.itmo.lab_4.utils;

public class InstantiationException extends RuntimeException {

    public enum Reason {
        INVALID_SUBTYPE,
        INVALID_CONSTRUCTOR
    }

    private final Class inputClass;
    private final Class targetObjectClass;
    private final Reason reason;
    private final Exception originalException;

    public InstantiationException(Class inputClass, Class targetObjectClass, Reason reason, Exception originalException) {
        super("Failed to instantiate class \"%s\" casting to \"%s\" because %s"
                .formatted(inputClass.getName(), targetObjectClass.getName(), reason.name()));
        this.inputClass = inputClass;
        this.targetObjectClass = targetObjectClass;
        this.originalException = originalException;
        this.reason = reason;
    }

    public Class getInputClass() {
        return inputClass;
    }

    public Class getTargetObjectClass() {
        return targetObjectClass;
    }

    public Exception getOriginalException() {
        return originalException;
    }

    public Reason getReason() {
        return reason;
    }
}
