package ru.bardinpetr.itmo.lab_4.realitylib.creatures;

import ru.bardinpetr.itmo.lab_4.realitylib.abilities.interfaces.Describable;
import ru.bardinpetr.itmo.lab_4.realitylib.creatures.interfaces.Heightable;
import ru.bardinpetr.itmo.lab_4.realitylib.creatures.interfaces.NameSettable;
import ru.bardinpetr.itmo.lab_4.realitylib.creatures.interfaces.Nameable;
import ru.bardinpetr.itmo.lab_4.realitylib.things.PhysicalObject;

import java.util.Objects;

abstract public class Creature extends PhysicalObject implements Nameable, NameSettable, Heightable, Describable {
    private String firstname = "";
    private String lastname = "";
    private String patronymic = "";
    private double height;

    public Creature(String name, String patronymic, String surname) {
        setName(name, patronymic, surname);
    }

    public Creature(String name) {
        setName(name);
    }

    @Override
    public String getName() {
        return firstname;
    }

    @Override
    public String getFullName() {
        StringBuilder sb = new StringBuilder();
        if (!firstname.equals("")) sb.append("%s ".formatted(firstname));
        if (!patronymic.equals("")) sb.append("%s ".formatted(patronymic));
        if (!lastname.equals("")) sb.append("%s ".formatted(lastname));
        return sb.toString().strip();
    }

    @Override
    public final void setName(String firstname, String patronymic, String lastname) {
        this.firstname = firstname;
        this.patronymic = patronymic;
        this.lastname = lastname;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public void setHeight(double newHeight) {
        height = newHeight;
    }

    @Override
    public String getPhysicalObjectName() {
        return getFullName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Creature creature = (Creature) o;

        if (Double.compare(creature.height, height) != 0) return false;
        if (!Objects.equals(firstname, creature.firstname)) return false;
        if (!Objects.equals(lastname, creature.lastname)) return false;
        return Objects.equals(patronymic, creature.patronymic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), firstname, lastname, patronymic, height);
    }
}
