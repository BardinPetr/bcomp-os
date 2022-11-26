package ru.bardinpetr.itmo.lab_4.abilities.interfaces;

import ru.bardinpetr.itmo.lab_4.abilities.Ability;
import ru.bardinpetr.itmo.lab_4.abilities.errors.AbilityExistsException;
import ru.bardinpetr.itmo.lab_4.abilities.errors.AbilityNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface IAble {
    Map<String, Ability> getModifiedAbilities();

    Map<Class, Ability> getPureAbilities();

    /**
     * Adds an ability to pure group. Only one ability of each type can be added.
     *
     * @param ability ability to add
     */
    default void addAbility(Ability ability) {
        // This part allows deprecated ability names to be used as earlier.
        // Should be removed when getAbilityName support is dropped.
        if (!ability.getAbilityName().isEmpty()) {
            addAbility(ability.getAbilityName(), ability);
            return;
        }

        Ability res = getPureAbilities().putIfAbsent(ability.getClass(), ability);
        if (res != null)
            throw new AbilityExistsException(res);
    }

    default void addAbility(String name, Ability ability) {
        Ability res = getModifiedAbilities().putIfAbsent(name, ability);
        if (res != null)
            throw new AbilityExistsException(res);
    }

    /**
     * Returns all abilities including pure and modified by concatenation
     *
     * @return all abilities as List
     */
    default List<Ability> getAbilities() {
        List<Ability> res = new ArrayList<>();
        res.addAll(getPureAbilities().values());
        res.addAll(getModifiedAbilities().values());
        return res;
    }

    @Deprecated
    default Ability getAbility(String type) {
        for (Ability ability : getAbilities()) if (ability.getAbilityType().equals(type)) return ability;
        return null;
    }

    /**
     * Searches an ability with specific name in named and typed abilities
     *
     * @param name ability's name set with addAbility(String, Ability).
     *             Name in Ability object is Deprecated and not used here
     * @return found ability if exists
     */
    default Ability getAbilityByName(String name) {
        Ability res = getModifiedAbilities().get(name);
        if (res == null)
            throw new AbilityNotFoundException(AbilityNotFoundException.SearchType.NAME, name);
        return res;
    }

    default Ability getAbility(Class abilityClass) throws AbilityNotFoundException {
        Ability res = getPureAbilities().get(abilityClass);
        if (res == null)
            throw new AbilityNotFoundException(AbilityNotFoundException.SearchType.TYPE, abilityClass.getName());
        return res;
    }
}
