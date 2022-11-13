package ru.bardinpetr.itmo.lab_3;

import ru.bardinpetr.itmo.lab_3.abilities.Ability;
import ru.bardinpetr.itmo.lab_3.abilities.actions.CureAction;
import ru.bardinpetr.itmo.lab_3.abilities.actions.HelpAction;
import ru.bardinpetr.itmo.lab_3.abilities.actions.LikeAction;
import ru.bardinpetr.itmo.lab_3.abilities.actions.TalkAction;
import ru.bardinpetr.itmo.lab_3.creatures.Creature;
import ru.bardinpetr.itmo.lab_3.creatures.animals.Dog;
import ru.bardinpetr.itmo.lab_3.creatures.humans.Human;
import ru.bardinpetr.itmo.lab_3.creatures.humans.HumanGroup;
import ru.bardinpetr.itmo.lab_3.creatures.humans.ProfessionHuman;
import ru.bardinpetr.itmo.lab_3.creatures.humans.professions.*;
import ru.bardinpetr.itmo.lab_3.properties.models.*;
import ru.bardinpetr.itmo.lab_3.properties.modifiers.*;
import ru.bardinpetr.itmo.lab_3.things.PhysicalObject;
import ru.bardinpetr.itmo.lab_3.things.Thing;
import ru.bardinpetr.itmo.lab_3.things.Weapon;
import ru.bardinpetr.itmo.lab_3.things.place.House;
import ru.bardinpetr.itmo.lab_3.things.place.Place;
import ru.bardinpetr.itmo.lab_3.things.wear.Clothing;
import ru.bardinpetr.itmo.lab_3.things.wear.WearType;

public class App {
    public static void main(String[] args) {
        HumanGroup malyshi = new HumanGroup("Малыши");

        Place house = new House("домик", new double[]{1.1, 2.2}, 100);

        // Doctor Pilulkin
        ProfessionHuman pilulkin = new Doctor("Пилюлькин");
        malyshi.add(pilulkin);

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

//        pilulkin.doProfession();

        // Mechanics
        ProfessionHuman vintik = new Mechanic("Винтик");
        malyshi.add(vintik);

        vintik.applyModifier(Popularity.POPULAR);

        ProfessionHuman shpuntik = new Helper("Шпунтик");
        malyshi.add(shpuntik);

        ((HelpAction) shpuntik.getProfessionalAbility()).setMaster(vintik);


        // Syropchik
        Human siropchik = new Human("Сахарин", "Сахариныч", "Сиропчик");
        malyshi.add(siropchik);


        siropchik.applyModifier(Politeness.POLITE);

        // soda-related
        Thing water = new Thing("вода");
        Thing syrop = new Thing("сироп");

        water.applyModifier(WaterType.SODA);
        water.applyModifier(new HasModifier(syrop));

        Ability likeSoda = new LikeAction(water);
        siropchik.addAbility(likeSoda);

        siropchik.applyModifier(new KnownForModifier(likeSoda));


        // Pul'ka
        Thing bullet = new Thing("пробка");
        Weapon gun = new Weapon("ружье", bullet);

        Hunter pulka = new Hunter("Пулька", gun);
        malyshi.add(pulka);

        Creature bulka = new Dog("Булька");
        bulka.applyModifier(Size.SMALL);

        pulka.have(bulka);


        // Neznaika

        Human neznaika = new Human("Незнайка");

        malyshi.add(neznaika);

        neznaika.applyModifier(new MostOfModifier(Popularity.POPULAR, malyshi));

        Thing paints = new Thing("краски");
        paints.applyModifier(Brightness.BRIGHT);
        neznaika.addAbility(new LikeAction(paints));

        neznaika.applyModifier(new ReasonModifier(new NameModifier("Незнайка"), "он ничего не знал"));

        // neznaika's wear
        Clothing hat = new Clothing(WearType.HAT, 42);
        hat.applyModifier(Color.CYAN);
        neznaika.wear(hat);

        Clothing trousers = new Clothing(WearType.TROUSERS, 42);
        trousers.applyModifier(Color.YELLOW_CANARY);
        neznaika.wear(trousers);

        Clothing shirt = new Clothing(WearType.SHIRT, 42);
        shirt.applyModifier(Color.ORANGE);

        Clothing tie = new Clothing(WearType.TIE, 24);
        tie.applyModifier(Color.GREEN);
        shirt.applyModifier(new HasModifier(tie));

        neznaika.wear(shirt);

        // Gunka

        Human gunka = new Human("Гунька");
        malyshi.add(gunka);

        neznaika.addFriend(gunka);
        gunka.addFriend(neznaika);

        gunka.live(new Place("ул. Маргариток", new double[]{1.1, 2.1}));

        Ability ngTalk = new TalkAction();
        ngTalk.applyModifier(new TimeModifier("часами"));

        neznaika.addAbility(ngTalk);
//        neznaika.getAbility(TalkAction.TYPE).performOn(gunka);


        // Malyshki
        HumanGroup malyshki = new HumanGroup("Малышки");
        neznaika


        // Many others

        String[] names = new String[]{"Торопыжка", "Ворчун", "Молчун", "Пончик", "Растеряйка", "Авоська", "Небоська"};
        for (int i = 0; i < names.length; i++)
            malyshi.add(new Human(names[i]));

        ProfessionHuman tubik = new Artist("Тюбик");
        malyshi.add(tubik);

        ProfessionHuman guslya = new Musician("Гусля");
        malyshi.add(guslya);

        malyshi.getByName("Авоська").addBrother(malyshi.getByName("Небоська"));

        malyshi.live(house);




/*
В этом же домике жил известный доктор Пилюлькин, который лечил коротышек от всех болезней.
+
Он всегда ходил в белом халате, а на голове носил белый колпак с кисточкой.
+
Жил здесь [домик] также знаменитый механик Винтик со своим помощником Шпунтиком;
+
жил [домик] Сахарин Сахариныч Сиропчик, который прославился тем, что очень любил газированную воду с сиропом.
+
Он [Сиропчик] был очень вежливый.
+
Ему [Сиропчик] нравилось, когда его называли по имени и отчеству, и не нравилось, когда кто-нибудь называл его просто Сиропчиком.
!!!!
Жил еще в этом доме охотник Пулька. У него была маленькая собачка Булька и еще было ружье, которое стреляло пробками.
+
Жил художник Тюбик, музыкант Гусля и другие малыши: Торопыжка, Ворчун, Молчун, Пончик, Растеряйка, два брата -- Авоська и Небоська.
+
Но самым известным среди них был малыш, по имени Незнайка.
+
Его прозвали Незнайкой за то, что он ничего не знал.
+
Этот Незнайка носил яркую голубую шляпу, желтые, канареечные, брюки и оранжевую рубашку с зеленым галстуком.
+
Он вообще любил яркие краски.
+
Нарядившись таким попугаем, Незнайка по целым дням слонялся по городу, сочинял разные небылицы и всем рассказывал.

Кроме того, он постоянно обижал малышек.

Поэтому малышки, завидев издали его оранжевую рубашку, сейчас же поворачивали в обратную сторону и прятались по домам.

У Незнайки был друг, по имени Гунька, который жил на улице Маргариток.
+
С Гунькой Незнайка мог болтать по целым часам.
+
Они двадцать раз на день ссорились между собой и двадцать раз на день мирились.

 */
    }
}
