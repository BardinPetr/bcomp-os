package ru.bardinpetr.itmo.lab_3.properties.modifiers;

import ru.bardinpetr.itmo.lab_3.properties.models.Color;
import ru.bardinpetr.itmo.lab_3.properties.models.Popularity;
import ru.bardinpetr.itmo.lab_3.properties.interfaces.IModifier;

public class ColorModifier implements IModifier {
    public static final String TYPE = "color";
    private Color color;

    public ColorModifier(Color color) {
        this.color = color;
    }

    @Override
    public String getPreposition() {
        return "";
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public String getValue() {
        return color.name();
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
