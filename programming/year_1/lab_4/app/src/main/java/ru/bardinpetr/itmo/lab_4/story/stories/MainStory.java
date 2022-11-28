package ru.bardinpetr.itmo.lab_4.story.stories;

import ru.bardinpetr.itmo.lab_4.realitylib.abilities.Ability;
import ru.bardinpetr.itmo.lab_4.realitylib.creatures.Creature;
import ru.bardinpetr.itmo.lab_4.realitylib.creatures.humans.Human;
import ru.bardinpetr.itmo.lab_4.realitylib.creatures.humans.HumanGroup;
import ru.bardinpetr.itmo.lab_4.realitylib.creatures.humans.ProfessionHuman;
import ru.bardinpetr.itmo.lab_4.realitylib.properties.modifiers.HasModifier;
import ru.bardinpetr.itmo.lab_4.realitylib.story.Story;
import ru.bardinpetr.itmo.lab_4.realitylib.story.annotations.dependency.StoryProvide;
import ru.bardinpetr.itmo.lab_4.realitylib.story.annotations.initialization.SetupMethod;
import ru.bardinpetr.itmo.lab_4.realitylib.things.PhysicalObject;
import ru.bardinpetr.itmo.lab_4.realitylib.things.Thing;
import ru.bardinpetr.itmo.lab_4.realitylib.things.place.Place;
import ru.bardinpetr.itmo.lab_4.realitylib.things.wear.Clothing;
import ru.bardinpetr.itmo.lab_4.realitylib.things.wear.WearType;
import ru.bardinpetr.itmo.lab_4.story.actions.CureAction;
import ru.bardinpetr.itmo.lab_4.story.actions.HelpAction;
import ru.bardinpetr.itmo.lab_4.story.actions.LikeAction;
import ru.bardinpetr.itmo.lab_4.story.creatures.animals.Dog;
import ru.bardinpetr.itmo.lab_4.story.creatures.humans.professions.*;
import ru.bardinpetr.itmo.lab_4.story.modifiers.models.*;
import ru.bardinpetr.itmo.lab_4.story.modifiers.modifiers.*;
import ru.bardinpetr.itmo.lab_4.story.things.places.House;
import ru.bardinpetr.itmo.lab_4.story.things.tools.Weapon;

public class MainStory extends Story {

    // Groups
    @StoryProvide
    private final HumanGroup allLittleManGroup = new HumanGroup("коротышки");
    @StoryProvide
    private final HumanGroup littleBoyGroup = new HumanGroup("малыши");
    @StoryProvide
    private final HumanGroup littleGirlsGroup = new HumanGroup("малышки");

    // Actors
    @StoryProvide
    private final ProfessionHuman pilulkin = new Doctor("Пилюлькин");
    @StoryProvide
    private final ProfessionHuman vintik = new Mechanic("Винтик");

    @StoryProvide
    private final Human siropchik = new Human("Сахарин", "Сахариныч", "Сиропчик");
    @StoryProvide
    private final Hunter pulka = new Hunter("Пулька", null);
    @StoryProvide
    private final Human neznaika = new Human("Незнайка");
    @StoryProvide
    private final Human znaika = new Human("Знайка");
    @StoryProvide
    private final Human gunka = new Human("Гунька");
    @StoryProvide
    private final ProfessionHuman guslya = new Musician("Гусля");
    @StoryProvide
    private final ProfessionHuman tubik = new Artist("Тюбик");
    @StoryProvide
    private final Human avoska = new Human("Авоська");
    @StoryProvide
    private final Human neboska = new Human("Небоська");

    // Place
    @StoryProvide
    private final Place margaritokStreet = new Place("ул. Маргариток", new double[]{1.1, 2.1});

    @StoryProvide
    private final Place kolokolchikovStreet = new Place("ул. Колокольчиков", new double[]{1.1, 2.1});

    @StoryProvide
    private final Place city = new Place("город", new double[]{0, 0});

    @StoryProvide
    private final Place house = new House("домик", new double[]{1.2, 1.5}, 100);

    @StoryProvide
    private final Place otherHouse = new House("домик на 16м.", new double[]{1.2, 1.5}, 16);

    // Include sectiob

    @StoryProvide
    private final Story forestStory = new ForestStory();

    @StoryProvide
    private final Story inCityStory = new CityStory();

    @StoryProvide
    private final Story znaikaStory = new ZnaikaStory();

    @StoryProvide
    private final Story neznaikaStory = new NeznaikaStory();

    public MainStory() {
        super("MAIN");
    }

    @SetupMethod
    private void setupHouses() {
        otherHouse.applyModifier(new PlaceModifier(kolokolchikovStreet));
    }

    @SetupMethod
    private void setupPilulkin() {
        allLittleManGroup.add(pilulkin);

        ((CureAction) pilulkin.getProfessionalAbility()).addCuredIllness(Illness.ANY);

        pilulkin.applyModifier(Popularity.POPULAR);

        Clothing coat = new Clothing(WearType.MEDICAL_GOWN, 42);
        coat.applyModifier(Color.WHITE);

        Clothing cap = new Clothing(WearType.CAP, 42);
        PhysicalObject brush = new Thing("кисточка");
        cap.applyModifier(new HasModifier(brush));
        cap.applyModifier(Color.WHITE);

        pilulkin.wear(coat);
        pilulkin.wear(cap);
    }

    @SetupMethod
    private void setupMechanics() {
        vintik.applyModifier(Popularity.POPULAR);

        ProfessionHuman shpuntik = new Helper("Шпунтик");

        ((HelpAction) shpuntik.getProfessionalAbility()).setMaster(vintik);

        allLittleManGroup.add(shpuntik);
        allLittleManGroup.add(vintik);
    }

    @SetupMethod
    private void setupSiropchik() {
        siropchik.applyModifier(Politeness.POLITE);

        Thing water = new Thing("вода");
        Thing syrop = new Thing("сироп");

        water.applyModifier(WaterType.SODA);
        water.applyModifier(new HasModifier(syrop));

        Ability likeSoda = new LikeAction(water);
        likeSoda.applyModifier(Degree.VERY);
        siropchik.addAbility("like soda", likeSoda);

        siropchik.applyModifier(new KnownForModifier(likeSoda));
        allLittleManGroup.add(siropchik);
    }

    @SetupMethod
    private void setupPulka() {
        Thing bullet = new Thing("пробка");
        Weapon gun = new Weapon("ружье", bullet);

        allLittleManGroup.add(pulka);

        Creature bulka = new Dog("Булька");
        bulka.applyModifier(Size.SMALL);

        pulka.have(bulka);
    }

    @SetupMethod
    private void setupNeznaika() {
        allLittleManGroup.add(neznaika);

        neznaika.applyModifier(new MostOfModifier(Popularity.POPULAR, allLittleManGroup));

        Thing paints = new Thing("краски");
        paints.applyModifier(Brightness.BRIGHT);
        neznaika.addAbility(new LikeAction(paints));

        neznaika.applyModifier(new ReasonModifier(new NameModifier("Незнайка"), "он ничего не знал"));

        neznaika.addFriend(gunka);
    }

    @SetupMethod
    private void setupGunka() {
        gunka.live(margaritokStreet);
        allLittleManGroup.add(gunka);
        gunka.addFriend(neznaika);
    }

    @SetupMethod
    private void setupNeznaikaWear() {
        Clothing neznaikaHat = new Clothing(WearType.HAT, 42);
        neznaikaHat.applyModifier(Color.CYAN);
        neznaika.wear(neznaikaHat);

        Clothing neznaikaTrousers = new Clothing(WearType.TROUSERS, 42);
        neznaikaTrousers.applyModifier(Color.YELLOW_CANARY);
        neznaika.wear(neznaikaTrousers);

        Clothing neznaikaShirt = new Clothing(WearType.SHIRT, 42);
        neznaikaShirt.applyModifier(Color.ORANGE);

        Clothing neznaikaTie = new Clothing(WearType.TIE, 24);
        neznaikaTie.applyModifier(Color.GREEN);
        neznaikaShirt.applyModifier(new HasModifier(neznaikaTie));

        neznaika.wear(neznaikaShirt);
    }

    @SetupMethod
    private void setupManyOthers() {
        String[] names = new String[]{"Торопыжка", "Ворчун", "Молчун", "Пончик", "Растеряйка"};
        for (String name : names) allLittleManGroup.add(new Human(name));

        allLittleManGroup.add(tubik);
        allLittleManGroup.add(guslya);
        allLittleManGroup.add(avoska);
        allLittleManGroup.add(neboska);

        allLittleManGroup.getByName("Авоська").addBrother(allLittleManGroup.getByName("Небоська"));

        allLittleManGroup.live(house);
    }
}
