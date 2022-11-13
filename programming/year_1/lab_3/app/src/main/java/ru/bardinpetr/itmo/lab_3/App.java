package ru.bardinpetr.itmo.lab_3;

import ru.bardinpetr.itmo.lab_3.abilities.actions.cure.CureAction;
import ru.bardinpetr.itmo.lab_3.properties.models.Illness;
import ru.bardinpetr.itmo.lab_3.human.professions.Doctor;
import ru.bardinpetr.itmo.lab_3.properties.interfaces.IModifier;
import ru.bardinpetr.itmo.lab_3.properties.models.Color;
import ru.bardinpetr.itmo.lab_3.properties.models.Popularity;
import ru.bardinpetr.itmo.lab_3.properties.modifiers.ColorModifier;
import ru.bardinpetr.itmo.lab_3.properties.modifiers.HasModifier;
import ru.bardinpetr.itmo.lab_3.properties.modifiers.PopularityModifier;
import ru.bardinpetr.itmo.lab_3.things.PhysicalObject;
import ru.bardinpetr.itmo.lab_3.things.Thing;
import ru.bardinpetr.itmo.lab_3.things.place.House;
import ru.bardinpetr.itmo.lab_3.things.place.Place;
import ru.bardinpetr.itmo.lab_3.things.wear.Clothing;
import ru.bardinpetr.itmo.lab_3.things.wear.WearType;

public class App {
    public static void main(String[] args) {
        Place house = new House("домик", new double[]{1.1, 2.2}, 100);

        // Doctor Pilulkin
        Doctor pilulkin = new Doctor("Пилюлькин");

        ((CureAction) pilulkin.getProfessionalAbility())
                .addCuredIllness(Illness.ANY);

        pilulkin.live(house);

        pilulkin.applyModifier(new PopularityModifier(Popularity.POPULAR));

        IModifier white = new ColorModifier(Color.WHITE);

        Clothing coat = new Clothing(WearType.MEDICAL_GOWN, 42);
        coat.applyModifier(white);

        Clothing cap = new Clothing(WearType.CAP, 42);
        PhysicalObject brush = new Thing("кисточка");
        cap.applyModifier(new HasModifier(brush));
        cap.applyModifier(white);

        pilulkin.wear(coat);
        pilulkin.wear(cap);

        //        pilulkin.getAbility(Cure.TYPE).performOn(group);

        System.out.println(pilulkin.describe());

/*
В этом же домике жил известный доктор Пилюлькин, который лечил коротышек от всех болезней.
+
Он всегда ходил в белом халате, а на голове носил белый колпак с кисточкой.
+
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
