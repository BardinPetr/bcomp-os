package ru.bardinpetr.itmo.lab_4.story.things.tools;

import ru.bardinpetr.itmo.lab_4.realitylib.things.PhysicalObject;
import ru.bardinpetr.itmo.lab_4.realitylib.things.Thing;
import ru.bardinpetr.itmo.lab_4.realitylib.things.tool.Tool;

import java.util.Objects;

public class Weapon extends Tool {
    private final Thing bullet;

    public Weapon(String name, Thing bullet) {
        super(name);
        this.bullet = bullet;
    }

    public Weapon(String name) {
        this(name, new Thing("пули"));
    }

    @Override
    public String describe() {
        return "%s стреляющее %s".formatted(getPhysicalObjectName(), bullet.describe());
    }

    public String apply(PhysicalObject target) {
        return "стрелять %s в %s".formatted(bullet.getPhysicalObjectName(), target);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Weapon weapon = (Weapon) o;

        return Objects.equals(bullet, weapon.bullet);
    }


    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), bullet);
    }

    @Override
    public String toString() {
        return "Weapon{bullet=%s} %s".formatted(bullet, super.toString());
    }
}
