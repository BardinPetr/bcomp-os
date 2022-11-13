package ru.bardinpetr.itmo.lab_3;

import ru.bardinpetr.itmo.lab_3.abilities.Ability;
import ru.bardinpetr.itmo.lab_3.abilities.actions.*;
import ru.bardinpetr.itmo.lab_3.abilities.interfaces.Describable;
import ru.bardinpetr.itmo.lab_3.creatures.Creature;
import ru.bardinpetr.itmo.lab_3.creatures.animals.Animal;
import ru.bardinpetr.itmo.lab_3.creatures.animals.Dog;
import ru.bardinpetr.itmo.lab_3.creatures.humans.Human;
import ru.bardinpetr.itmo.lab_3.creatures.humans.HumanGroup;
import ru.bardinpetr.itmo.lab_3.creatures.humans.ProfessionHuman;
import ru.bardinpetr.itmo.lab_3.creatures.humans.professions.*;
import ru.bardinpetr.itmo.lab_3.properties.interfaces.IModifier;
import ru.bardinpetr.itmo.lab_3.properties.models.*;
import ru.bardinpetr.itmo.lab_3.properties.modifiers.*;
import ru.bardinpetr.itmo.lab_3.scenarios.Scenario;
import ru.bardinpetr.itmo.lab_3.things.PhysicalObject;
import ru.bardinpetr.itmo.lab_3.things.Thing;
import ru.bardinpetr.itmo.lab_3.things.place.House;
import ru.bardinpetr.itmo.lab_3.things.place.Place;
import ru.bardinpetr.itmo.lab_3.things.tool.Weapon;
import ru.bardinpetr.itmo.lab_3.things.wear.Clothing;
import ru.bardinpetr.itmo.lab_3.things.wear.WearType;

public class App {
    public static void main(String[] args) {
        HumanGroup allGroup = new HumanGroup("Малыши");
        HumanGroup girlsGroup = new HumanGroup("Малышки");

        Place city = new Place("город", new double[]{0, 0});
        Place house = new House("домик", new double[]{1.1, 2.2}, 100);

        // Doctor Pilulkin
        ProfessionHuman pilulkin = new Doctor("Пилюлькин");
        allGroup.add(pilulkin);

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


        // Mechanics
        ProfessionHuman vintik = new Mechanic("Винтик");
        allGroup.add(vintik);

        vintik.applyModifier(Popularity.POPULAR);

        ProfessionHuman shpuntik = new Helper("Шпунтик");
        allGroup.add(shpuntik);

        ((HelpAction) shpuntik.getProfessionalAbility()).setMaster(vintik);


        // Syropchik
        Human siropchik = new Human("Сахарин", "Сахариныч", "Сиропчик");
        allGroup.add(siropchik);

        siropchik.applyModifier(Politeness.POLITE);

        Describable dummyAction = () -> "происходящее";

        Ability syrLikeAbility = new LikeAction(dummyAction);
        syrLikeAbility.setAbilityName("like naming");
        siropchik.addAbility(syrLikeAbility);

        Ability syrDislikeAbility = new LikeAction(dummyAction, false);
        syrDislikeAbility.setAbilityName("dislike naming");
        siropchik.addAbility(syrDislikeAbility);

        Scenario syrScenario =
                new Scenario()
                        .newSentence()
                        .addIf("называть его по имени и отчеству")
                        .addThen(siropchik.performByName("like naming"))
                        .addElse()
                        .addThen(siropchik.performByName("dislike naming"));

        siropchik.addScenario(syrScenario);

        // soda-related
        Thing water = new Thing("вода");
        Thing syrop = new Thing("сироп");

        water.applyModifier(WaterType.SODA);
        water.applyModifier(new HasModifier(syrop));

        Ability likeSoda = new LikeAction(water);
        likeSoda.applyModifier(Degree.VERY);
        siropchik.addAbility(likeSoda);

        siropchik.applyModifier(new KnownForModifier(likeSoda));


        // Pul'ka
        Thing bullet = new Thing("пробка");
        Weapon gun = new Weapon("ружье", bullet);

        Hunter pulka = new Hunter("Пулька", gun);
        allGroup.add(pulka);

        Creature bulka = new Dog("Булька");
        bulka.applyModifier(Size.SMALL);

        pulka.have(bulka);


        // Neznaika

        Human neznaika = new Human("Незнайка");

        allGroup.add(neznaika);

        neznaika.applyModifier(new MostOfModifier(Popularity.POPULAR, allGroup));

        Thing paints = new Thing("краски");
        paints.applyModifier(Brightness.BRIGHT);
        neznaika.addAbility(new LikeAction(paints));

        neznaika.applyModifier(new ReasonModifier(new NameModifier("Незнайка"), "он ничего не знал"));

        // neznaika's wear
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


        // neznaika actions;

        neznaika.addAbility(new LookLikeAction());
        PhysicalObject parrot = new Animal("попугай");

        Ability neznaikaWalk = new WalkAction(city);
        neznaikaWalk.setAbilityName("neznaika walk");
        neznaikaWalk.applyModifier(new TimeModifier("целыми днями"));
        neznaika.addAbility(neznaikaWalk);

        Ability createNebilitsa = new ThinkAction("сочинение небылиц");
        createNebilitsa.setAbilityName("create nebilitsa");
        neznaika.addAbility(createNebilitsa);

        Ability talkOfNebilitsa = new SayAction("небылицы");
        talkOfNebilitsa.setAbilityName("speak nebilitsa");
        talkOfNebilitsa.applyModifier(new TargetModifier("всем"));
        neznaika.addAbility(talkOfNebilitsa);

        neznaika.addAbility(new OffendAction());
        neznaika.getAbility(OffendAction.TYPE)
                .applyModifier(new TimeModifier("постоянно"));

        Scenario neznaikaInCityScenario =
                new Scenario()
                        .newSentence()
                        .addIf(neznaika.performByType(WearAction.TYPE))
                        .addThen(neznaika.performByTypeWithOn(LookLikeAction.TYPE, null, parrot))
                        .addThen(neznaika.performByName(neznaikaWalk.getAbilityName()))
                        .addThen(neznaika.performByName(createNebilitsa.getAbilityName()))
                        .addThen(neznaika.performByName(talkOfNebilitsa.getAbilityName()))
                        .newSentence()
                        .addThen(neznaika.performByTypeWithOn(OffendAction.TYPE, null, girlsGroup));

        neznaika.addScenario(neznaikaInCityScenario);

        // Gunka

        Human gunka = new Human("Гунька");
        allGroup.add(gunka);

        neznaika.addFriend(gunka);
        gunka.addFriend(neznaika);

        gunka.live(new Place("ул. Маргариток", new double[]{1.1, 2.1}));

        Ability ngTalk = new TalkAction();
        ngTalk.setAbilityName("ngTalk");
        ngTalk.applyModifier(new TimeModifier("часами"));

        neznaika.addAbility(ngTalk);
        gunka.addAbility(ngTalk);

        IModifier countPerDay = new CountModifier(20);

        Ability quarrelOn = new QuarrelAction(QuarrelState.IN_QUARREL);
        quarrelOn.setAbilityName("quarrelOn");
        quarrelOn.applyModifier(countPerDay);
        neznaika.addAbility(quarrelOn);
        gunka.addAbility(quarrelOn);

        Ability quarrelOff = new QuarrelAction(QuarrelState.NORMAL);
        quarrelOff.applyModifier(countPerDay);
        quarrelOff.setAbilityName("quarrelOff");
        neznaika.addAbility(quarrelOff);
        gunka.addAbility(quarrelOff);

        neznaika.addScenario(new Scenario()
                .newSentence()
                .addThen(neznaika.performByNameWithOn("ngTalk", null, gunka))
                .newSentence()
                .addThen(neznaika.performByNameWithOn("quarrelOn", null, gunka))
                .addThen(neznaika.performByNameWithOn("quarrelOff", null, gunka)));

        gunka.addScenario(new Scenario()
                .newSentence()
                .addThen(gunka.performByNameWithOn("ngTalk", null, neznaika))
                .newSentence()
                .addThen(gunka.performByNameWithOn("quarrelOn", null, neznaika))
                .addThen(gunka.performByNameWithOn("quarrelOff", null, neznaika)));

        // Many others
        String[] names = new String[]{"Торопыжка", "Ворчун", "Молчун", "Пончик", "Растеряйка", "Авоська", "Небоська"};
        for (int i = 0; i < names.length; i++)
            allGroup.add(new Human(names[i]));

        ProfessionHuman tubik = new Artist("Тюбик");
        allGroup.add(tubik);

        ProfessionHuman guslya = new Musician("Гусля");
        allGroup.add(guslya);

        allGroup.getByName("Авоська").addBrother(allGroup.getByName("Небоська"));

        allGroup.live(house);


        // malyshki
        girlsGroup.addAbility(new SeeAction());

        Ability malyshkiTurn = new TurnAction("обратно");
        malyshkiTurn.applyModifier(new TimeModifier("сейчас же"));
        girlsGroup.addAbility(malyshkiTurn);

        girlsGroup.addAbility(new HideAction(house));

        Scenario malyshkiWithNeznaika =
                new Scenario()
                        .newSentence()
                        .addIf(girlsGroup.performByTypeWithOn(SeeAction.TYPE, null, neznaika))
                        .addThen(girlsGroup.performByType(TurnAction.TYPE))
                        .addThen(girlsGroup.performByType(HideAction.TYPE));

        girlsGroup.addScenario(malyshkiWithNeznaika);

        // Presentation
        System.out.println(allGroup.describe());
        System.out.println(girlsGroup.describe());
    }
}

/*
    Исходный текст
        В этом же домике жил известный доктор Пилюлькин, который лечил коротышек от всех болезней.
        Он всегда ходил в белом халате, а на голове носил белый колпак с кисточкой.
        Жил здесь [домик] также знаменитый механик Винтик со своим помощником Шпунтиком;
        жил [в домик] Сахарин Сахариныч Сиропчик, который прославился тем, что очень любил газированную воду с сиропом.
        Он [Сиропчик] был очень вежливый.
        Ему [Сиропчик] нравилось, когда его называли по имени и отчеству, и не нравилось, когда кто-нибудь называл его просто Сиропчиком.
        Жил еще в этом доме охотник Пулька. У него была маленькая собачка Булька и еще было ружье, которое стреляло пробками.
        Жил художник Тюбик, музыкант Гусля и другие малыши: Торопыжка, Ворчун, Молчун, Пончик, Растеряйка, два брата -- Авоська и Небоська.
        Но самым известным среди них был малыш, по имени Незнайка.
        Его прозвали Незнайкой за то, что он ничего не знал.
        Этот Незнайка носил яркую голубую шляпу, желтые, канареечные, брюки и оранжевую рубашку с зеленым галстуком.
        Он вообще любил яркие краски.
        Нарядившись таким попугаем, Незнайка по целым дням слонялся по городу, сочинял разные небылицы и всем рассказывал.
        Кроме того, он постоянно обижал малышек.
        Поэтому малышки, завидев издали его оранжевую рубашку, сейчас же поворачивали в обратную сторону и прятались по домам.
        У Незнайки был друг, по имени Гунька, который жил на улице Маргариток.
        С Гунькой Незнайка мог болтать по целым часам.
        Они двадцать раз на день ссорились между собой и двадцать раз на день мирились.
*/
