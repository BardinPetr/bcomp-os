package ru.bardinpetr.itmo.lab_4.abilities;

import java.util.HashMap;
import java.util.Map;

public record AbilityResult(boolean status, String resultText, Map<String, Object> data) {
    public AbilityResult(String text) {
        this(true, text, new HashMap<>());
    }

    public AbilityResult(boolean status) {
        this(status, status ? "успешно" : "неуспешно", new HashMap<>());
    }
}
