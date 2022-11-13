package ru.bardinpetr.itmo.lab_3.properties.modifiers;

import ru.bardinpetr.itmo.lab_3.properties.interfaces.IModifier;

public class ReasonModifier implements IModifier {
    private final IModifier modifier;

    private final String reason;

    public ReasonModifier(IModifier modifier, String reason) {
        this.modifier = modifier;
        this.reason = reason;
    }

    @Override
    public String getType() {
        return "по причине";
    }

    @Override
    public String getValue() {
        return reason;
    }

    @Override
    public String describe() {
        return "%s %s имелись свойства: %s".formatted(getType(), getValue(), modifier.describe());
    }

    public String getReason() {
        return reason;
    }
}
