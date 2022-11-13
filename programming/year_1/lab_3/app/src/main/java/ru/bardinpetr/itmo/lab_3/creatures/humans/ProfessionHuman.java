package ru.bardinpetr.itmo.lab_3.creatures.humans;


import ru.bardinpetr.itmo.lab_3.abilities.Ability;
import ru.bardinpetr.itmo.lab_3.creatures.humans.interfaces.Professionable;

public abstract class ProfessionHuman extends Human implements Professionable {
    private final Ability professionalAbility;

    public ProfessionHuman(String name, String patronymic, String surname, Ability professionalAbility) {
        super(name, patronymic, surname);
        this.professionalAbility = professionalAbility;
        addAbility(this.professionalAbility);
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
}
