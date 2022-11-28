package ru.bardinpetr.itmo.lab_4.story.modifiers.modifiers;

import ru.bardinpetr.itmo.lab_4.realitylib.abilities.interfaces.Describable;
import ru.bardinpetr.itmo.lab_4.realitylib.properties.interfaces.IModifier;

import java.util.Objects;

public class ReasonModifier implements IModifier {
    private IModifier modifier = null;

    private Describable reason;

    public ReasonModifier(IModifier modifier, String reason) {
        this.modifier = modifier;
        this.reason = () -> reason;
    }

    public ReasonModifier(IModifier modifier, Describable reason) {
        this.modifier = modifier;
        this.reason = reason;
    }

    public ReasonModifier(Describable reason) {
        this.reason = reason;
    }

    @Override
    public String getType() {
        return "по причине";
    }

    @Override
    public String getValue() {
        return "\"%s\"%s".formatted(getReason(),
                modifier == null ? "" : "имелись свойства: (%s)".formatted(modifier.describe()));
    }

    public String getReason() {
        return reason.describe();
    }

    public IModifier getModifier() {
        return modifier;
    }

    public void setModifier(IModifier modifier) {
        this.modifier = modifier;
    }

    public void setReason(Describable reason) {
        this.reason = reason;
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
