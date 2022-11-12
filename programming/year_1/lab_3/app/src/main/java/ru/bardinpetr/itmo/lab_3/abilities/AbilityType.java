package ru.bardinpetr.itmo.lab_3.abilities;


import ru.bardinpetr.itmo.lab_3.abilities.interfaces.Performable;

public enum AbilityType implements Performable {
    LIVE {
        @Override
        public String perform() {
            return "живет";
        }
    }, WEAR {
    }, CURE;

    @Override
    public String perform() {
        return this.name();
    }
}
