package ru.bardinpetr.itmo.lab_3.properties.modifiers;

import ru.bardinpetr.itmo.lab_3.properties.models.Popularity;
import ru.bardinpetr.itmo.lab_3.properties.interfaces.IModifier;

public class PopularityModifier implements IModifier {
    public static final String TYPE = "popularity";
    private Popularity popularity;

    public PopularityModifier(Popularity popularity) {
        this.popularity = popularity;
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
        return popularity.name();
    }

    public void setPopularity(Popularity popularity) {
        this.popularity = popularity;
    }
}
