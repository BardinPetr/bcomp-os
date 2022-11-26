package ru.bardinpetr.itmo.lab_4.utils;

import ru.bardinpetr.itmo.lab_4.abilities.Ability;
import ru.bardinpetr.itmo.lab_4.abilities.errors.PureAbilityInstantiationException;

import java.lang.reflect.InvocationTargetException;

public class AbilityHelper {
    public static Ability instantiatePureAbility(Class ability) throws PureAbilityInstantiationException {
        try {
            return (Ability) ability.getConstructor(new Class[]{}).newInstance();
        } catch (NoSuchMethodException |
                 InvocationTargetException |
                 InstantiationException |
                 IllegalAccessException e) {
            throw new PureAbilityInstantiationException(ability, e);
        }
    }
}
