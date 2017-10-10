package doyenm.zooshell.controller.animalcontroller.evaluation;

import doyenm.zooshell.context.AnimalEvaluationContext;
import doyenm.zooshell.controller.animalcontroller.evaluation.death.AnimalDeathPredicates;
import doyenm.zooshell.controller.animalcontroller.evaluation.death.AnimalUpdateDyingMeasures;
import doyenm.zooshell.controller.eventhandling.AnimalEvent;
import doyenm.zooshell.controller.eventhandling.AnimalEventType;
import doyenm.zooshell.model.Animal;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class AnimalDeathEvaluationController
        implements Function<AnimalEvaluationContext, AnimalEvaluationContext> {

    private final AnimalUpdateDyingMeasures measures; 
    private final AnimalDeathPredicates deathPredicates; 

    @Override
    public AnimalEvaluationContext apply(AnimalEvaluationContext t) {
        AnimalEvaluationContext context = t;
        Animal animal = measures.updateIsDyingByDrowning(context.getAnimal());
        animal = measures.updateIsDyingByHunger(animal, context.getKeepers());
        animal = measures.updateIsDeadByPredation(animal, context.getAnimalsOfThePaddock());
        context.setAnimal(animal);
        Map<AnimalEventType, Boolean> events = generateEvents(context.getAnimal());

        events.keySet()
                .stream()
                .forEach((AnimalEventType eventType) -> {
                    if (events.get(eventType)) {
                        context.getEvents().add(new AnimalEvent(eventType, context.getAnimal(), context.getAnimal().getKiller()));
                    }
                });

        context.setDead(isDead(animal));
        return context;
    }

    private Map<AnimalEventType, Boolean> generateEvents(Animal animal) {
        Map<AnimalEventType, Boolean> events = new HashMap<>();
        events.put(AnimalEventType.DEATH_OF_AGE, deathPredicates.isDeadByOldAge(animal));
        events.put(AnimalEventType.DEATH_OF_DROWN, deathPredicates.isDeadByDrowning(animal));
        events.put(AnimalEventType.DEATH_OF_HUNGER, deathPredicates.isDeadByHunger(animal));
        events.put(AnimalEventType.DEATH_OF_PREDATION, deathPredicates.isDeadByPredation(animal));
        events.put(AnimalEventType.DIYING_OF_DROWN, animal.getDaysOfDrowning() != 0);
        events.put(AnimalEventType.DIYING_OF_HUNGER, animal.getDaysOfHunger()!= 0);
        return events;
    }
    
    private boolean isDead(Animal animal){
        return deathPredicates.isDeadByDrowning(animal)
                || deathPredicates.isDeadByHunger(animal)
                || deathPredicates.isDeadByOldAge(animal)
                || deathPredicates.isDeadByPredation(animal);
    }

}
