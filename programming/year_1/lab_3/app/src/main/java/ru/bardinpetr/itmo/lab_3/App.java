package ru.bardinpetr.itmo.lab_3;

import ru.bardinpetr.itmo.lab_3.abilities.AbilityType;
import ru.bardinpetr.itmo.lab_3.human.LivingHuman;
import ru.bardinpetr.itmo.lab_3.properties.ModifierType;
import ru.bardinpetr.itmo.lab_3.things.place.House;
import ru.bardinpetr.itmo.lab_3.things.place.Place;
import ru.bardinpetr.itmo.lab_3.things.wear.Clothing;
import ru.bardinpetr.itmo.lab_3.things.wear.WearType;
import ru.bardinpetr.itmo.lab_3.things.wear.Wearable;

public class App {
    public static void main(String[] args) {

        Place house = new House("домик", new double[]{1.1, 2.2}, 100);

        LivingHuman pilulkin = new LivingHuman("Пилюлькин");
        pilulkin.addAbility(AbilityType.LIVE)
                .applyModifier(ModifierType.PLACE, house);

        Wearable coat = new Clothing(WearType.MEDICAL_GOWN, 55);
        pilulkin.addAbility(AbilityType.WEAR)
                .applyModifier(ModifierType.WHAT, coat);

        pilulkin.addAbility(AbilityType.CURE)
                .applyModifier(ModifierType.FROM, "все болезни");

        System.out.println(pilulkin.describe());
/*
В этом же домике жил известный доктор Пилюлькин, который лечил коротышек от всех болезней.

Он всегда ходил в белом халате, а на голове носил белый колпак с кисточкой.

Жил здесь [домик] также знаменитый механик Винтик со своим помощником Шпунтиком;

жил [домик] Сахарин Сахариныч Сиропчик, который прославился тем, что очень любил газированную воду с сиропом.

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
    }
}
