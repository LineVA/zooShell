package doyenm.zooshell.controller.animalcontroller.evaluation;

import doyenm.zooshell.context.AnimalEvaluationContext;
import doyenm.zooshell.controller.animalcontroller.evaluation.death.AnimalDeathPredicates;
import doyenm.zooshell.controller.animalcontroller.evaluation.death.AnimalDyingPredicates;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.AnimalKeeper;
import java.util.Collection;
import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class AnimalDeathEvaluationController
        implements Function<AnimalEvaluationContext, AnimalEvaluationContext> {

    AnimalDeathPredicates deathPredicates = new AnimalDeathPredicates();
    AnimalDyingPredicates dyingPredicates = new AnimalDyingPredicates();

    @Override
    public AnimalEvaluationContext apply(AnimalEvaluationContext t) {
        AnimalEvaluationContext context = t;
        Animal animal = this.updateIsDyingByDrowning(context.getAnimal());
        animal = this.updateIsDyingByHunger(animal, context.getKeepers());
        context.setAnimal(animal);
        boolean isDead = deathPredicates.isDeadByAge(animal)
                || deathPredicates.isDeadByDrowning(animal)
                || deathPredicates.isDeadByHunger(animal);
        context.setDead(isDead);
        return context;
    }

    private Animal updateIsDyingByDrowning(Animal animal) {
        if (dyingPredicates.isDyingByDrowning(animal)) {
            animal.setDaysOfDrowning(animal.getDaysOfDrowning() + 1);
        } else {
            animal.setDaysOfDrowning(0);
        }
        return animal;
    }

    private Animal updateIsDyingByHunger(Animal animal, Collection<AnimalKeeper> keepers) {
        if (dyingPredicates.isDyingByFast(animal) |
                dyingPredicates.isDyingByBadDiets(animal) |
                dyingPredicates.isDyingByFoodQuantityToZero(animal) |
                dyingPredicates.isDyingByLackOfKeeper(animal, keepers)) {
            animal.setDaysOfHunger(animal.getDaysOfHunger() + 1);
        } else {
            animal.setDaysOfHunger(0);
        }
        return animal;
    }
}
