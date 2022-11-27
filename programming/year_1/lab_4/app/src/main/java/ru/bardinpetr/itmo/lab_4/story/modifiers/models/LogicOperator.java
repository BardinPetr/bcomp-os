package ru.bardinpetr.itmo.lab_4.story.modifiers.models;

public enum LogicOperator {
    AND("и"), OR("или");

    private final String text;

    LogicOperator(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }


}
