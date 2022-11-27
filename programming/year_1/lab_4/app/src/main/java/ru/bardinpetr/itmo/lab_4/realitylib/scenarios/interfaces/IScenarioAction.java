package ru.bardinpetr.itmo.lab_4.realitylib.scenarios.interfaces;

import ru.bardinpetr.itmo.lab_4.realitylib.abilities.interfaces.Describable;
import ru.bardinpetr.itmo.lab_4.realitylib.scenarios.StoryContext;

public interface IScenarioAction extends Describable {
    /**
     * A shortcut of execute(Context) but without context
     *
     * @return result of execution
     */
    default String execute() {
        return execute(null);
    }

    /**
     * Runs the underlying activity's execute which should change state of context and/or executor
     */
    String execute(StoryContext context);

    /**
     * Do not invoke any state-changing methods, just describe what will happen if execute() is called
     * Also this should not use any sort of context
     *
     * @return description of actions
     */
    @Override
    default String describe() {
        return execute();
    }
}