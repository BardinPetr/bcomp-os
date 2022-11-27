package ru.bardinpetr.itmo.lab_4.story.modifiers.modifiers;

import ru.bardinpetr.itmo.lab_4.realitylib.abilities.interfaces.Describable;
import ru.bardinpetr.itmo.lab_4.realitylib.properties.interfaces.IAlteringModifier;

import java.util.Objects;

public class ActivityReasonModifier implements IAlteringModifier {
    private Describable reason;

    public ActivityReasonModifier(String reason) {
        this.reason = () -> reason;
    }

    public ActivityReasonModifier(Describable reason) {
        this.reason = reason;
    }

    @Override
    public String getType() {
        return "по причине";
    }

    @Override
    public String getValue() {
        return getReason();
    }

    @Override
    public void setValue(Object value) {
        reason = (Describable) value;
    }

    public String getReason() {
        return reason.describe();
    }

    public void setReason(Describable reason) {
        this.reason = reason;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ActivityReasonModifier that = (ActivityReasonModifier) o;

        return Objects.equals(reason, that.reason);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reason);
    }

    @Override
    public String toString() {
        return "ActivityReasonModifier{reason='%s'}".formatted(reason);
    }
}
