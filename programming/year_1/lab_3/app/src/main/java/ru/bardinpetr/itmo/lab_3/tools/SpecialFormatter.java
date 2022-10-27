package ru.bardinpetr.itmo.lab_3.tools;

import ru.bardinpetr.itmo.lab_3.abilities.interfaces.Describable;

import java.util.List;

public class SpecialFormatter {

    public static String format(List<? extends Describable> data) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < data.size(); i++) {
            sb.append(data.get(i).describe());
            if (i != data.size() - 1) sb.append(", ");
        }

        return sb.toString();
    }

    public static String joinNullableStrings(String sep, String... data) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            if (data[i] == null || data[i].isEmpty()) continue;
            sb.append(data[i]);
            if (i != data.length - 1)
                sb.append(sep);
        }
        return sb.toString();
    }
}
