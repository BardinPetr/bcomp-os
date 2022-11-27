package ru.bardinpetr.itmo.lab_4.realitylib.properties.errors;

public class ModifierNotEditableException extends RuntimeException {
    private final Class targetModifierClass;


    public ModifierNotEditableException(Class targetModifierClass) {
        super(("Modifier of class %s should not be changed. " +
                "Please use modifier's constructor and per-field getters/setters")
                .formatted(targetModifierClass.getName()));
        this.targetModifierClass = targetModifierClass;
    }

    public Class getTargetModifierClass() {
        return targetModifierClass;
    }
}
