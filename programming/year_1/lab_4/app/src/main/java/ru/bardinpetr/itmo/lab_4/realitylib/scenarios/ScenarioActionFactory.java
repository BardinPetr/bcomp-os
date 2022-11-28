package ru.bardinpetr.itmo.lab_4.realitylib.scenarios;

import ru.bardinpetr.itmo.lab_4.realitylib.abilities.Ability;
import ru.bardinpetr.itmo.lab_4.realitylib.abilities.errors.AbilityExecutionException;
import ru.bardinpetr.itmo.lab_4.realitylib.scenarios.interfaces.IAbilityConfigurationRunnable;
import ru.bardinpetr.itmo.lab_4.realitylib.scenarios.interfaces.IScenarioAction;
import ru.bardinpetr.itmo.lab_4.realitylib.things.PhysicalObject;

public class ScenarioActionFactory {
    private static final ScenarioActionFactory instance = new ScenarioActionFactory();

    public static ScenarioActionFactory getInstance() {
        return instance;
    }

    public IScenarioAction newScenarioAction(PhysicalObject actor, Ability ability, IAbilityConfigurationRunnable conf) {
        return new IScenarioAction() {
            @Override
            public String execute(StoryContext context) {
                var configured = conf.configure(ability, context);
                try {
                    var result = configured.execute(actor);
                    return "%s %s".formatted(actor.getPhysicalObjectName(), result.resultText());
                } catch (AbilityExecutionException ex) {
                    return "%s failed doing %s".formatted(
                            ex.getExecutor().getPhysicalObjectName(),
                            ex.getAbility().getName()
                    );
                }
            }

            @Override
            public String describe() {
                var description = conf.configure(ability, null).describe();
                return "%s %s".formatted(actor.getPhysicalObjectName(), description);
            }
        };
    }
}
