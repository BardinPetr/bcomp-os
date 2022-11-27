package ru.bardinpetr.itmo.lab_4.properties.errors;

public class ModifierNotFoundException extends RuntimeException {
    private final Class targetModifierClass;


    public ModifierNotFoundException(Class targetModifierClass) {
        super("Modifier of class %s not found".formatted(targetModifierClass.getName()));
        this.targetModifierClass = targetModifierClass;
    }

    public Class getTargetModifierClass() {
        return targetModifierClass;
    }
}
