package doyenm.zooshell.controller.animalcontroller;

import doyenm.zooshell.context.AnimalUpdateFastDaysContext;

import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class AnimalUpdateFastDaysController
        implements Function<AnimalUpdateFastDaysContext, AnimalUpdateFastDaysContext> {

    @Override
    public AnimalUpdateFastDaysContext apply(AnimalUpdateFastDaysContext t) {
        t.getConvertedAnimal().getCurrentFoodAttributes().setFastDays(t.getConvertedFastDays());
        t.getZoo().getAnimals().replace(t.getAnimal().toUpperCase(), t.getConvertedAnimal());
        return t;
    }

}
