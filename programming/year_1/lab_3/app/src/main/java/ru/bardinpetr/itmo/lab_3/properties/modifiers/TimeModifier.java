package ru.bardinpetr.itmo.lab_3.properties.modifiers;

import ru.bardinpetr.itmo.lab_3.properties.interfaces.IModifier;

import java.util.Objects;

public class TimeModifier implements IModifier {
    private final String time;

    public TimeModifier(String time) {
        this.time = time;
    }

    @Override
    public String getType() {
        return "время";
    }

    @Override
    public String getValue() {
        return time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimeModifier that = (TimeModifier) o;

        return Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return time != null ? time.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "TimeModifier{time='%s'}".formatted(time);
    }
}
