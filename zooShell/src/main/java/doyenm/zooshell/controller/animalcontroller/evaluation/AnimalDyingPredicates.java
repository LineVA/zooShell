package doyenm.zooshell.controller.animalcontroller.evaluation;

import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.PaddockType;

/**
 *
 * @author doyenm
 */
public class AnimalDyingPredicates {

    private final int NUMBER_OF_DAYS_PER_WEEK = 7;

    public boolean isDyingByDrowning(Animal animal) {
        return PaddockType.AQUARIUM == animal.getPaddock().getType();
    }

    public boolean isDyingByFast(Animal animal) {
        return animal.getCurrentFoodAttributes().getFastDays() == NUMBER_OF_DAYS_PER_WEEK;
    }

}
