package ru.bardinpetr.itmo.lab_4.story.actions;

import ru.bardinpetr.itmo.lab_4.realitylib.abilities.Ability;
import ru.bardinpetr.itmo.lab_4.realitylib.abilities.AbilityResult;
import ru.bardinpetr.itmo.lab_4.realitylib.creatures.humans.Human;
import ru.bardinpetr.itmo.lab_4.realitylib.scenarios.interfaces.IScenarioAction;

public class WantAction extends Ability {

    private IScenarioAction wantedAction;

    @Override
    protected String getVerb() {
        return "хотеть";
    }

    @Override
    protected String getDescription() {
        return wantedAction.describe();
    }

    @Override
    public AbilityResult execute(Human self) {
        return new AbilityResult(describe());
    }

    public IScenarioAction getWantedAction() {
        return wantedAction;
    }

    public WantAction setWantedAction(IScenarioAction wantedAction) {
        this.wantedAction = wantedAction;
        return this;
    }
}
