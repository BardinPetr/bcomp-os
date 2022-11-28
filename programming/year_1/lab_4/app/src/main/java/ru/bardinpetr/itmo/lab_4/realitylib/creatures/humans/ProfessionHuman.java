package ru.bardinpetr.itmo.lab_4.realitylib.creatures.humans;


import ru.bardinpetr.itmo.lab_4.realitylib.abilities.Ability;
import ru.bardinpetr.itmo.lab_4.realitylib.creatures.humans.interfaces.Professionable;

import java.util.Objects;

public abstract class ProfessionHuman extends Human implements Professionable {
    private final Ability professionalAbility;

    public ProfessionHuman(String name, String patronymic, String surname, Ability professionalAbility) {
        super(name, patronymic, surname);
        this.professionalAbility = professionalAbility;
        addAbility("profession", this.professionalAbility);
    }

    public ProfessionHuman(String name, Ability professionalAbility) {
        this(name, "", "", professionalAbility);
    }

    @Override
    public Ability getProfessionalAbility() {
        return professionalAbility;
    }

    public String doProfession() {
        return getProfessionalAbility().perform();
    }

    abstract public String getProfessionName();

    @Override
    public String describe() {
        return "%s %s".formatted(getProfessionName(), super.describe());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ProfessionHuman that = (ProfessionHuman) o;

        return Objects.equals(professionalAbility, that.professionalAbility);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), professionalAbility);
    }

    @Override
    public String toString() {
        return "ProfessionHuman{professionalAbility=%s, %s}".formatted(professionalAbility, super.toString());
    }
}
