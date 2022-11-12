package ru.bardinpetr.itmo.lab_3.human;

import ru.bardinpetr.itmo.lab_3.abilities.interfaces.Describable;
import ru.bardinpetr.itmo.lab_3.human.interfaces.Heightable;
import ru.bardinpetr.itmo.lab_3.human.interfaces.NameSettable;
import ru.bardinpetr.itmo.lab_3.human.interfaces.Nameable;

import ru.bardinpetr.itmo.lab_3.things.PhysicalObject;
import ru.bardinpetr.itmo.lab_3.tools.SpecialFormatter;

abstract public class BaseHuman extends PhysicalObject implements Nameable, NameSettable, Heightable, Describable {
    private String firstname = "";
    private String lastname = "";
    private String patronymic = "";
    private double height;

    public BaseHuman(String name, String patronymic, String surname) {
        setName(name, patronymic, surname);
    }

    public BaseHuman(String name) {
        setName(name);
    }

    @Override
    public String getName() {
        return firstname;
    }

    @Override
    public String getFullName() {
        return SpecialFormatter.joinNullableStrings(" ", firstname, patronymic, lastname);
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
}
