package ru.bardinpetr.itmo.lab_3.things;

public class Weapon extends Tool {
    private final Thing bullet;

    public Weapon(String name, Thing bullet) {
        super(name);
        this.bullet = bullet;
    }

    public Weapon(String name) {
        this(name, new Thing("пули"));
    }

    public String apply(PhysicalObject target) {
        return "стрелять %s в %s".formatted(bullet.getPhysicalObjectName(), target);
    }
}
