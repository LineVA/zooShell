package doyenm.zooshell.controller.animalcontroller.evaluation;

import doyenm.zooshell.context.AnimalEvaluationContext;
import doyenm.zooshell.model.Animal;
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
        animal = this.updateIsDyingByFast(animal);
        context.setAnimal(animal);
        boolean isDead = deathPredicates.isDeadByAge(animal)
                || deathPredicates.isDeadByDrowning(animal)
                || deathPredicates.isDeadByFast(animal);
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

    private Animal updateIsDyingByFast(Animal animal) {
        if (dyingPredicates.isDyingByFast(animal)) {
            animal.setDaysOfFast(animal.getDaysOfFast() + 1);
        } else {
            animal.setDaysOfFast(0);
        }
        return animal;
    }
}
