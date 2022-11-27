package ru.bardinpetr.itmo.lab_4.realitylib.utils;

import ru.bardinpetr.itmo.lab_4.realitylib.abilities.Ability;
import ru.bardinpetr.itmo.lab_4.realitylib.properties.interfaces.IModifier;

import java.lang.reflect.InvocationTargetException;

public class InstantiationHelper {
    public static Ability instantiatePureAbility(Class ability) throws InstantiationException {
        if (!Ability.class.isAssignableFrom(ability))
            throw new InstantiationException(ability, Ability.class, InstantiationException.Reason.INVALID_SUBTYPE, null);
        try {
            return (Ability) ability.getConstructor(new Class[]{}).newInstance();
        } catch (NoSuchMethodException |
                 InvocationTargetException |
                 java.lang.InstantiationException |
                 IllegalAccessException e) {
            throw new InstantiationException(ability, Ability.class, InstantiationException.Reason.INVALID_CONSTRUCTOR, e);
        }
    }

    public static IModifier instantiateModifier(Class modifier) throws InstantiationException {
        if (!IModifier.class.isAssignableFrom(modifier))
            throw new InstantiationException(modifier, IModifier.class, InstantiationException.Reason.INVALID_SUBTYPE, null);
        try {
            return (IModifier) modifier.getConstructor(new Class[]{}).newInstance();
        } catch (NoSuchMethodException |
                 InvocationTargetException |
                 java.lang.InstantiationException |
                 IllegalAccessException e) {
            throw new InstantiationException(modifier, IModifier.class, InstantiationException.Reason.INVALID_CONSTRUCTOR, e);
        }
    }
}
