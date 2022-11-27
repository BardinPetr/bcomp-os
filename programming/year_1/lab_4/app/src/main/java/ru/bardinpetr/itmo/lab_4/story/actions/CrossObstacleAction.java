package ru.bardinpetr.itmo.lab_4.story.actions;

import ru.bardinpetr.itmo.lab_4.realitylib.abilities.AbilityResult;
import ru.bardinpetr.itmo.lab_4.realitylib.abilities.TargetedAbility;
import ru.bardinpetr.itmo.lab_4.realitylib.creatures.humans.Human;
import ru.bardinpetr.itmo.lab_4.realitylib.things.PhysicalObject;
import ru.bardinpetr.itmo.lab_4.realitylib.things.Thing;
import ru.bardinpetr.itmo.lab_4.realitylib.things.tool.Tool;

public class CrossObstacleAction extends TargetedAbility {

    private CrossType moveType = CrossType.VIA_GROUD;
    private Tool tool = null;

    @Override
    protected String getVerb() {
        return "пере%s".formatted(moveType);
    }

    @Override
    protected String getDescription() {
        if(tool == null)
            return targetObject.describe();
        else
            return tool.apply(targetObject);
    }

    public CrossType getMoveType() {
        return moveType;
    }

    public CrossObstacleAction setMoveType(CrossType moveType) {
        this.moveType = moveType;
        return this;
    }

    public Tool getTool() {
        return tool;
    }

    public CrossObstacleAction setTool(Tool tool) {
        this.tool = tool;
        return this;
    }

    @Override
    public CrossObstacleAction setTargetObject(PhysicalObject targetObject) {
        return (CrossObstacleAction) super.setTargetObject(targetObject);
    }

    @Override
    public AbilityResult execute(Human self) {
        self.setPosition(this.targetObject.getPosition());
        return new AbilityResult(describe());
    }

    public enum CrossType {
        VIA_GROUD("перебираться"),
        VIA_WATER("переплывать"),
        VIA_AIR("перелетать");

        private final String text;

        CrossType(String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }
    }
}
