package doyenm.zooshell.controller.animalcontroller;

import doyenm.zooshell.context.AnimalUpdateFoodQuantityContext;
import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class AnimalUpdateFoodQuantityController
        implements Function<AnimalUpdateFoodQuantityContext, AnimalUpdateFoodQuantityContext> {

    @Override
    public AnimalUpdateFoodQuantityContext apply(AnimalUpdateFoodQuantityContext t) {
        t.getConvertedAnimal().getCurrentFoodAttributes().setQuantity(t.getConvertedFoodQuantity());
        t.getZoo().getAnimals().replace(t.getAnimal().toUpperCase(), t.getConvertedAnimal());
        return t;
    }

}
