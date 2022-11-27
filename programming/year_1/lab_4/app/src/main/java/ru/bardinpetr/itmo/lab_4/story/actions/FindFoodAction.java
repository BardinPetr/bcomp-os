package ru.bardinpetr.itmo.lab_4.story.actions;

import ru.bardinpetr.itmo.lab_4.realitylib.abilities.Ability;
import ru.bardinpetr.itmo.lab_4.realitylib.abilities.AbilityResult;
import ru.bardinpetr.itmo.lab_4.realitylib.creatures.humans.Human;
import ru.bardinpetr.itmo.lab_4.story.things.food.Eatable;

public class FindFoodAction extends Ability {

    private Eatable[] foodTypes = new Eatable[]{};

    @Override
    protected String getVerb() {
        return "искать еду";
    }

    @Override
    protected String getDescription() {
        var builder = new StringBuilder("следующих видов: ");
        for (var i : foodTypes) builder.append("%s, ".formatted(i.describe()));
        return builder.toString();
    }

    public FindFoodAction setFoodTypes(Eatable[] foodTypes) {
        this.foodTypes = foodTypes;
        return this;
    }

    public Eatable[] getFoodTypes() {
        return foodTypes;
    }
}
