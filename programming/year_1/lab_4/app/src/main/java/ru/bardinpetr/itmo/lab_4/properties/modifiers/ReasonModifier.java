package ru.bardinpetr.itmo.lab_4.properties.modifiers;

import ru.bardinpetr.itmo.lab_4.properties.interfaces.IModifier;

import java.util.Objects;

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
        return "\"%s\" имелись свойства: (%s)".formatted(reason, modifier.describe());
    }

    public String getReason() {
        return reason;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReasonModifier that = (ReasonModifier) o;

        if (!Objects.equals(modifier, that.modifier)) return false;
        return Objects.equals(reason, that.reason);
    }


    @Override
    public int hashCode() {
        return Objects.hash(modifier, reason);
    }

    @Override
    public String toString() {
        return "ReasonModifier{modifier=%s, reason='%s'}".formatted(modifier.describe(), reason);
    }


}
