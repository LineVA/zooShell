package doyenm.zooshell.controller.animalcontroller.evaluation;

import doyenm.zooshell.context.AnimalEvaluationContext;
import doyenm.zooshell.controller.animalcontroller.evaluation.death.AnimalDeathPredicates;
import doyenm.zooshell.controller.animalcontroller.evaluation.death.AnimalUpdateDyingMeasures;
import doyenm.zooshell.controller.eventhandling.Event;
import doyenm.zooshell.controller.eventhandling.EventType;
import doyenm.zooshell.model.Animal;
import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class AnimalDeathEvaluationController
        implements Function<AnimalEvaluationContext, AnimalEvaluationContext> {

    AnimalUpdateDyingMeasures measures = new AnimalUpdateDyingMeasures();
    AnimalDeathPredicates deathPredicates = new AnimalDeathPredicates();

    @Override
    public AnimalEvaluationContext apply(AnimalEvaluationContext t) {
        AnimalEvaluationContext context = t;
        Animal animal = measures.updateIsDyingByDrowning(context.getAnimal());
        animal = measures.updateIsDyingByHunger(animal, context.getKeepers());
        context.setAnimal(animal);
        boolean isDead = false;
        if (deathPredicates.isDeadByOldAge(animal)) {
            isDead = true;
            context.getEvents().add(new Event(EventType.DEATH_OF_AGE, context.getAnimal()));
        }
        isDead |=  deathPredicates.isDeadByDrowning(animal)
                || deathPredicates.isDeadByHunger(animal);
        context.setDead(isDead);
        return context;
    }

}
