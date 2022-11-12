package ru.bardinpetr.itmo.lab_3.abilities.interfaces;

import ru.bardinpetr.itmo.lab_3.abilities.Ability;
import ru.bardinpetr.itmo.lab_3.abilities.AbilityType;
import ru.bardinpetr.itmo.lab_3.properties.interfaces.Modifiable;

import java.util.List;

public interface Able {
    void addAbility(Ability ability);

    default Ability addAbility(AbilityType abilityType) {
        Ability ability = new Ability(abilityType);
        addAbility(ability);
        return ability;
    }

    List<Ability> getAbilities();

    default Ability getAbility(AbilityType action) {
        for (Ability ability : getAbilities()) {
            if (ability.getType() == action)
                return ability;
        }
        return null;
    }
}
