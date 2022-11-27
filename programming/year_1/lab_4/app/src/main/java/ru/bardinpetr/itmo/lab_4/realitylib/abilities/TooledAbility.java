package ru.bardinpetr.itmo.lab_4.realitylib.abilities;

import ru.bardinpetr.itmo.lab_4.realitylib.things.PhysicalObject;
import ru.bardinpetr.itmo.lab_4.realitylib.things.tool.Tool;

import java.util.Objects;

public class TooledAbility extends TargetedAbility {

    protected Tool tool;

    public Tool getTool() {
        return tool;
    }

    public TooledAbility setTool(Tool tool) {
        this.tool = tool;
        return this;
    }

    @Override
    protected String getVerb() {
        return tool.apply(targetObject);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        TooledAbility that = (TooledAbility) o;

        return Objects.equals(tool, that.tool);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tool);
    }

    @Override
    public String toString() {
        return "TooledAbility{" +
                "tool=" + tool +
                "} " + super.toString();
    }
}
