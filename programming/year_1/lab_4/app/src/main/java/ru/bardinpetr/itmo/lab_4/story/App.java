package ru.bardinpetr.itmo.lab_4.story;

import ru.bardinpetr.itmo.lab_4.realitylib.abilities.Ability;
import ru.bardinpetr.itmo.lab_4.realitylib.abilities.interfaces.Describable;
import ru.bardinpetr.itmo.lab_4.realitylib.creatures.Creature;
import ru.bardinpetr.itmo.lab_4.realitylib.creatures.animals.Animal;
import ru.bardinpetr.itmo.lab_4.realitylib.creatures.humans.Human;
import ru.bardinpetr.itmo.lab_4.realitylib.creatures.humans.HumanGroup;
import ru.bardinpetr.itmo.lab_4.realitylib.creatures.humans.ProfessionHuman;
import ru.bardinpetr.itmo.lab_4.realitylib.properties.interfaces.IModifier;
import ru.bardinpetr.itmo.lab_4.realitylib.scenarios.Scenario;
import ru.bardinpetr.itmo.lab_4.realitylib.scenarios.TextualScenario;
import ru.bardinpetr.itmo.lab_4.realitylib.things.PhysicalObject;
import ru.bardinpetr.itmo.lab_4.realitylib.things.Thing;
import ru.bardinpetr.itmo.lab_4.realitylib.things.place.Place;
import ru.bardinpetr.itmo.lab_4.realitylib.things.wear.Clothing;
import ru.bardinpetr.itmo.lab_4.realitylib.things.wear.WearType;
import ru.bardinpetr.itmo.lab_4.story.actions.*;
import ru.bardinpetr.itmo.lab_4.story.creatures.animals.Dog;
import ru.bardinpetr.itmo.lab_4.story.creatures.humans.professions.*;
import ru.bardinpetr.itmo.lab_4.story.modifiers.models.*;
import ru.bardinpetr.itmo.lab_4.story.modifiers.modifiers.*;
import ru.bardinpetr.itmo.lab_4.story.stories.ForestStory;
import ru.bardinpetr.itmo.lab_4.story.things.places.House;
import ru.bardinpetr.itmo.lab_4.story.things.tools.Weapon;

public class App {
    public static void main(String[] args) {
        var fs = new ForestStory();
        fs.compile();
        fs.scenarioFindFood();

//        HumanGroup allGroup = new HumanGroup("Малыши");
//        HumanGroup girlsGroup = new HumanGroup("Малышки");
//
//        Place city = new Place("город", new double[]{0, 0});
//        Place house = new House("домик", new double[]{1.1, 2.2}, 100);
//
//        // Doctor Pilulkin
//        ProfessionHuman pilulkin = new Doctor("Пилюлькин");
//        allGroup.add(pilulkin);
//
//        ((CureAction) pilulkin.getProfessionalAbility()).addCuredIllness(Illness.ANY);
//
//        pilulkin.applyModifier(Popularity.POPULAR);
//
//        Clothing coat = new Clothing(WearType.MEDICAL_GOWN, 42);
//        coat.applyModifier(Color.WHITE);
//
//        Clothing cap = new Clothing(WearType.CAP, 42);
//        PhysicalObject brush = new Thing("кисточка");
//        cap.applyModifier(new HasModifier(brush));
//        cap.applyModifier(Color.WHITE);
//
//        pilulkin.wear(coat);
//        pilulkin.wear(cap);
//
//
//        // Mechanics
//        ProfessionHuman vintik = new Mechanic("Винтик");
//        allGroup.add(vintik);
//
//        vintik.applyModifier(Popularity.POPULAR);
//
//        ProfessionHuman shpuntik = new Helper("Шпунтик");
//        allGroup.add(shpuntik);
//
//        ((HelpAction) shpuntik.getProfessionalAbility()).setMaster(vintik);
//
//
//        // Syropchik
//        Human siropchik = new Human("Сахарин", "Сахариныч", "Сиропчик");
//        allGroup.add(siropchik);
//
//        siropchik.applyModifier(Politeness.POLITE);
//
//        Describable dummyAction = () -> "происходящее";
//
//        Ability syrLikeAbility = new LikeAction(dummyAction);
//        siropchik.addAbility("like naming", syrLikeAbility);
//
//        Ability syrDislikeAbility = new LikeAction(dummyAction, false);
//        siropchik.addAbility("dislike naming", syrDislikeAbility);
//
//        Scenario syrScenario =
//                new TextualScenario()
//                        .newSentence()
//                        .addIf("называть его по имени и отчеству")
//                        .addThen(siropchik.performByName("like naming"))
//                        .addElse()
//                        .addThen(siropchik.performByName("dislike naming"));
//
//        siropchik.addScenario(syrScenario);
//
//        // soda-related
//        Thing water = new Thing("вода");
//        Thing syrop = new Thing("сироп");
//
//        water.applyModifier(WaterType.SODA);
//        water.applyModifier(new HasModifier(syrop));
//
//        Ability likeSoda = new LikeAction(water);
//        likeSoda.applyModifier(Degree.VERY);
//        siropchik.addAbility(likeSoda);
//
//        siropchik.applyModifier(new KnownForModifier(likeSoda));
//
//
//        // Pul'ka
//        Thing bullet = new Thing("пробка");
//        Weapon gun = new Weapon("ружье", bullet);
//
//        Hunter pulka = new Hunter("Пулька", gun);
//        allGroup.add(pulka);
//
//        Creature bulka = new Dog("Булька");
//        bulka.applyModifier(Size.SMALL);
//
//        pulka.have(bulka);
//
//
//        // Neznaika
//
//        Human neznaika = new Human("Незнайка");
//
//        allGroup.add(neznaika);
//
//        neznaika.applyModifier(new MostOfModifier(Popularity.POPULAR, allGroup));
//
//        Thing paints = new Thing("краски");
//        paints.applyModifier(Brightness.BRIGHT);
//        neznaika.addAbility(new LikeAction(paints));
//
//        neznaika.applyModifier(new ReasonModifier(new NameModifier("Незнайка"), "он ничего не знал"));
//
//        // neznaika's wear
//        Clothing neznaikaHat = new Clothing(WearType.HAT, 42);
//        neznaikaHat.applyModifier(Color.CYAN);
//        neznaika.wear(neznaikaHat);
//
//        Clothing neznaikaTrousers = new Clothing(WearType.TROUSERS, 42);
//        neznaikaTrousers.applyModifier(Color.YELLOW_CANARY);
//        neznaika.wear(neznaikaTrousers);
//
//        Clothing neznaikaShirt = new Clothing(WearType.SHIRT, 42);
//        neznaikaShirt.applyModifier(Color.ORANGE);
//
//        Clothing neznaikaTie = new Clothing(WearType.TIE, 24);
//        neznaikaTie.applyModifier(Color.GREEN);
//        neznaikaShirt.applyModifier(new HasModifier(neznaikaTie));
//
//        neznaika.wear(neznaikaShirt);
//
//
//        // neznaika actions;
//
//        neznaika.addAbility(new LookLikeAction());
//        PhysicalObject parrot = new Animal("попугай");
//
//        Ability neznaikaWalk = new WalkAction();
//        neznaika.addAbility(neznaikaWalk);
//
//        Ability createNebilitsa = new ThinkAction("сочинение небылиц");
//        createNebilitsa.setAbilityName("create nebilitsa");
//        neznaika.addAbility(createNebilitsa);
//
//        Ability talkOfNebilitsa = new SayAction("небылицы");
//        talkOfNebilitsa.setAbilityName("speak nebilitsa");
//        talkOfNebilitsa.applyModifier(new TargetModifier("всем"));
//        neznaika.addAbility(talkOfNebilitsa);
//
//        neznaika.addAbility(new OffendAction());
//        neznaika.getAbility(OffendAction.TYPE)
//                .applyModifier(new TimeModifier("постоянно"));
//
//        Scenario neznaikaInCityScenario =
//                new TextualScenario()
//                        .newSentence()
//                        .addIf(neznaika.performByType(WearAction.TYPE))
//                        .addThen(neznaika.performByTypeWithOn(LookLikeAction.TYPE, null, parrot))
//                        .addThen(neznaika.perform(WalkAction.class,
//                                ((ability, ctx) -> ((WalkAction) ability)
//                                        .setPlace(city)
//                                        .applyModifier(new TimeModifier("целыми днями"))))
//                        )
//                        .addThen(neznaika.performByName(createNebilitsa.getAbilityName()))
//                        .addThen(neznaika.performByName(talkOfNebilitsa.getAbilityName()))
//                        .newSentence()
//                        .addThen(neznaika.performByTypeWithOn(OffendAction.TYPE, null, girlsGroup));
//
//        neznaika.addScenario(neznaikaInCityScenario);
//
//        // Gunka
//
//        Human gunka = new Human("Гунька");
//        allGroup.add(gunka);
//
//        neznaika.addFriend(gunka);
//        gunka.addFriend(neznaika);
//
//        gunka.live(new Place("ул. Маргариток", new double[]{1.1, 2.1}));
//
//        Ability ngTalk = new TalkAction();
//        ngTalk.setAbilityName("ngTalk");
//        ngTalk.applyModifier(new TimeModifier("часами"));
//
//        neznaika.addAbility(ngTalk);
//        gunka.addAbility(ngTalk);
//
//        IModifier countPerDay = new CountModifier(20);
//
//        Ability quarrelOn = new QuarrelAction(QuarrelState.IN_QUARREL);
//        quarrelOn.setAbilityName("quarrelOn");
//        quarrelOn.applyModifier(countPerDay);
//        neznaika.addAbility(quarrelOn);
//        gunka.addAbility(quarrelOn);
//
//        Ability quarrelOff = new QuarrelAction(QuarrelState.NORMAL);
//        quarrelOff.applyModifier(countPerDay);
//        quarrelOff.setAbilityName("quarrelOff");
//        neznaika.addAbility(quarrelOff);
//        gunka.addAbility(quarrelOff);
//
//        neznaika.addScenario(new TextualScenario()
//                .newSentence()
//                .addThen(neznaika.performByNameWithOn("ngTalk", null, gunka))
//                .newSentence()
//                .addThen(neznaika.performByNameWithOn("quarrelOn", null, gunka))
//                .addThen(neznaika.performByNameWithOn("quarrelOff", null, gunka)));
//
//        gunka.addScenario(new TextualScenario()
//                .newSentence()
//                .addThen(gunka.performByNameWithOn("ngTalk", null, neznaika))
//                .newSentence()
//                .addThen(gunka.performByNameWithOn("quarrelOn", null, neznaika))
//                .addThen(gunka.performByNameWithOn("quarrelOff", null, neznaika)));
//
////        h.perform(aid, (ability, ctx) -> ability.target(x).tool(y) )
////                .run()
////                .describe();
////
////        h.load(aid)
////                .target(x)
////                .tool(y)
////                .exec()
//
//
//        // Many others
//        String[] names = new String[]{"Торопыжка", "Ворчун", "Молчун", "Пончик", "Растеряйка", "Авоська", "Небоська"};
//        for (int i = 0; i < names.length; i++)
//            allGroup.add(new Human(names[i]));
//
//        ProfessionHuman tubik = new Artist("Тюбик");
//        allGroup.add(tubik);
//
//        ProfessionHuman guslya = new Musician("Гусля");
//        allGroup.add(guslya);
//
//        allGroup.getByName("Авоська").addBrother(allGroup.getByName("Небоська"));
//
//        allGroup.live(house);
//
//
//        // malyshki
//        girlsGroup.addAbility(new SeeAction());
//
//        Ability malyshkiTurn = new TurnAction("обратно");
//        malyshkiTurn.applyModifier(new TimeModifier("сейчас же"));
//        girlsGroup.addAbility(malyshkiTurn);
//
//        girlsGroup.addAbility(new HideAction(house));
//
//        Scenario malyshkiWithNeznaika =
//                new TextualScenario()
//                        .newSentence()
//                        .addIf(girlsGroup.performByTypeWithOn(SeeAction.TYPE, null, neznaika))
//                        .addThen(girlsGroup.performByType(TurnAction.TYPE))
//                        .addThen(girlsGroup.performByType(HideAction.TYPE));
//
//        girlsGroup.addScenario(malyshkiWithNeznaika);
//
//        // Presentation
//        System.out.println(allGroup.describe());
//        System.out.println(girlsGroup.describe());

        // River

//+ За рекой был лес.
//+ Коротышки делали из березовой коры лодочки, переплывали через реку и ходили в лес за ягодами, за грибами, за орехами.
//+ Собирать ягоды было трудно, потому что коротышки ведь были крошечные, а за орехами и вовсе приходилось лазить на высокий куст да еще тащить с собой пилу.
//+ Ни один коротышка не смог бы сорвать орех руками -- их надо было пилить пилой.
//+ Грибы тоже пилили пилой.
//+ Спилят гриб под самый корень, потом распилят его на части и тащат по кусочкам домой.

// Коротышки были неодинаковые: одни из них назывались малышами, а другие -- малышками.

// Малыши всегда ходили либо в длинных брюках навыпуск, либо в коротеньких штанишках на помочах, а малышки любили носить платьица из пестренькой, яркой материи.

// Малыши не любили возиться со своими прическами, и поэтому волосы у них были короткие, а у малышек волосы были длинные, чуть не до пояса.

// Малышки очень любили делать разные красивые прически, волосы заплетали в длинные косы и в косы вплетали ленточки, а на голове носили бантики.

// Многие малыши очень гордились тем, что они малыши, и совсем почти не дружили с малышками.

// А малышки гордились тем, что они малышки, и тоже не хотели дружить с малышами.

// Если какая-нибудь малышка встречала на улице малыша, то, завидев его издали, сейчас же переходила на другую сторону улицы.

// И хорошо делала, потому что среди малышей часто попадались такие, которые не могли спокойно пройти мимо малышки, а обязательно скажут ей что-нибудь обидное, даже толкнут или, еще того хуже, за косу дернут.

// Конечно, не все малыши были такие, но ведь этого на лбу у них не написано, поэтому малышки считали, что лучше заранее перейти на другую сторону улицы и не попадаться навстречу.

// За это многие малыши называли малышек воображульками -- придумают же такое слово! -- а многие малышки называли малышей забияками и другими обидными прозвищами.

// Некоторые читатели сразу скажут, что все это, наверно, выдумки, что в жизни таких малышей не бывает.

// Но никто ведь и не говорит, что они в жизни бывают.

// В жизни -- это одно, а в сказочном городе -- совсем другое.

// В сказочном городе все бывает.

// В одном домике на улице Колокольчиков жило шестнадцать малышей-коротышей.

// Самым главным из них был малыш-коротыш, по имени Знайка.

// Его прозвали Знайкой за то, что он знал очень много.

// А знал он много потому, что читал разные книги.

// Эти книги лежали у него и на столе, и под столом, и на кровати, и под кроватью.

// В его комнате не было такого места, где бы не лежали книги.

// От чтения книг Знайка сделался очень умным.

// Поэтому все его слушались и очень любили.

// Одевался он всегда в черный костюм, а когда садился за стол, надевал на нос очки и начинал читать какую-нибудь книгу, то совсем становился похож на профессора.

// В особенности Незнайка прославился после одной истории.

// Однажды он гулял по городу и забрел в поле.

// Вокруг не было ни души.

// В это время летел майский жук.

// Он сослепу налетел на Незнайку и ударил его по затылку.

// Незнайка кубарем покатился на землю.

// Жук в ту же минуту улетел и скрылся вдали.

// Незнайка вскочил, стал оглядываться по сторонам и смотреть, кто это его ударил.

// Но кругом никого не было.

// "Кто же это меня ударил? -- думал Незнайка.

// -- Может быть, сверху упало что-нибудь?" Он задрал голову и поглядел вверх, но вверху тоже ничего не было.

// Только солнце ярко сияло над головой у Незнайки.
    }
}

/*
Исходный текст
// За рекой был лес.

// Коротышки делали из березовой коры лодочки, переплывали через реку и ходили в лес за ягодами, за грибами, за орехами.

// Собирать ягоды было трудно, потому что коротышки ведь были крошечные, а за орехами и вовсе приходилось лазить на высокий куст да еще тащить с собой пилу.

// Ни один коротышка не смог бы сорвать орех руками -- их надо было пилить пилой.

// Грибы тоже пилили пилой.

// Спилят гриб под самый корень, потом распилят его на части и тащат по кусочкам домой.

// Коротышки были неодинаковые: одни из них назывались малышами, а другие -- малышками.

// Малыши всегда ходили либо в длинных брюках навыпуск, либо в коротеньких штанишках на помочах, а малышки любили носить платьица из пестренькой, яркой материи.

// Малыши не любили возиться со своими прическами, и поэтому волосы у них были короткие, а у малышек волосы были длинные, чуть не до пояса.

// Малышки очень любили делать разные красивые прически, волосы заплетали в длинные косы и в косы вплетали ленточки, а на голове носили бантики.

// Многие малыши очень гордились тем, что они малыши, и совсем почти не дружили с малышками.

// А малышки гордились тем, что они малышки, и тоже не хотели дружить с малышами.

// Если какая-нибудь малышка встречала на улице малыша, то, завидев его издали, сейчас же переходила на другую сторону улицы.

// И хорошо делала, потому что среди малышей часто попадались такие, которые не могли спокойно пройти мимо малышки, а обязательно скажут ей что-нибудь обидное, даже толкнут или, еще того хуже, за косу дернут.

// Конечно, не все малыши были такие, но ведь этого на лбу у них не написано, поэтому малышки считали, что лучше заранее перейти на другую сторону улицы и не попадаться навстречу.

// За это многие малыши называли малышек воображульками -- придумают же такое слово! -- а многие малышки называли малышей забияками и другими обидными прозвищами.

// Некоторые читатели сразу скажут, что все это, наверно, выдумки, что в жизни таких малышей не бывает.

// Но никто ведь и не говорит, что они в жизни бывают.

// В жизни -- это одно, а в сказочном городе -- совсем другое.

// В сказочном городе все бывает.

// В одном домике на улице Колокольчиков жило шестнадцать малышей-коротышей.

// Самым главным из них был малыш-коротыш, по имени Знайка.

// Его прозвали Знайкой за то, что он знал очень много.

// А знал он много потому, что читал разные книги.

// Эти книги лежали у него и на столе, и под столом, и на кровати, и под кроватью.

// В его комнате не было такого места, где бы не лежали книги.

// От чтения книг Знайка сделался очень умным.

// Поэтому все его слушались и очень любили.

// Одевался он всегда в черный костюм, а когда садился за стол, надевал на нос очки и начинал читать какую-нибудь книгу, то совсем становился похож на профессора.

+ В этом же домике жил известный доктор Пилюлькин, который лечил коротышек от всех болезней.
+ Он всегда ходил в белом халате, а на голове носил белый колпак с кисточкой.
+ Жил здесь также знаменитый механик Винтик со своим помощником Шпунтиком; жил Сахарин Сахариныч Сиропчик, который прославился тем, что очень любил газированную воду с сиропом.
+ Он был очень вежливый.
+ Ему нравилось, когда его называли по имени и отчеству, и не нравилось, когда кто-нибудь называл его просто Сиропчиком.
+ Жил еще в этом доме охотник Пулька.
+ У него была маленькая собачка Булька и еще было ружье, которое стреляло пробками.
+ Жил художник Тюбик, музыкант Гусля и другие малыши: Торопыжка, Ворчун, Молчун, Пончик, Растеряйка, два брата -- Авоська и Небоська.
+ Но самым известным среди них был малыш, по имени Незнайка.
+ Его прозвали Незнайкой за то, что он ничего не знал.
+ Этот Незнайка носил яркую голубую шляпу, желтые, канареечные, брюки и оранжевую рубашку с зеленым галстуком.
+ Он вообще любил яркие краски.
+ Нарядившись таким попугаем, Незнайка по целым дням слонялся по городу, сочинял разные небылицы и всем рассказывал.
+ Кроме того, он постоянно обижал малышек.
+ Поэтому малышки, завидев издали его оранжевую рубашку, сейчас же поворачивали в обратную сторону и прятались по домам.
+ У Незнайки был друг, по имени Гунька, который жил на улице Маргариток.
+ С Гунькой Незнайка мог болтать по целым часам.
+ Они двадцать раз на день ссорились между собой и двадцать раз на день мирились.
// В особенности Незнайка прославился после одной истории.

// Однажды он гулял по городу и забрел в поле.

// Вокруг не было ни души.

// В это время летел майский жук.

// Он сослепу налетел на Незнайку и ударил его по затылку.

// Незнайка кубарем покатился на землю.

// Жук в ту же минуту улетел и скрылся вдали.

// Незнайка вскочил, стал оглядываться по сторонам и смотреть, кто это его ударил.

// Но кругом никого не было.

// "Кто же это меня ударил? -- думал Незнайка.

// -- Может быть, сверху упало что-нибудь?" Он задрал голову и поглядел вверх, но вверху тоже ничего не было.

// Только солнце ярко сияло над головой у Незнайки.

*/
