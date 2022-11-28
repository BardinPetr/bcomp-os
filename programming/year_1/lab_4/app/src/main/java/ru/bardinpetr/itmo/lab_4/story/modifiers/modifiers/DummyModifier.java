package ru.bardinpetr.itmo.lab_4.story.modifiers.modifiers;

import ru.bardinpetr.itmo.lab_4.realitylib.abilities.interfaces.Describable;
import ru.bardinpetr.itmo.lab_4.realitylib.properties.interfaces.IAlteringModifier;
import ru.bardinpetr.itmo.lab_4.realitylib.properties.interfaces.IModifier;

import java.util.Objects;

public class DummyModifier implements IModifier {

    @Override
    public String getType() {
        return "";
    }

    @Override
    public String getValue() {
        return "";
    }
}
