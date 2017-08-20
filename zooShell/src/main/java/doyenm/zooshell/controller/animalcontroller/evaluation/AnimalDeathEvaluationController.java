package doyenm.zooshell.controller.animalcontroller.evaluation;

import doyenm.zooshell.context.AnimalEvaluationContext;
import doyenm.zooshell.controller.animalcontroller.evaluation.death.AnimalDeathPredicates;
import doyenm.zooshell.controller.animalcontroller.evaluation.death.AnimalUpdateDyingMeasures;
import doyenm.zooshell.controller.eventhandling.Event;
import doyenm.zooshell.controller.eventhandling.EventType;
import doyenm.zooshell.model.Animal;
import java.util.HashMap;
import java.util.Map;
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
        Map<EventType, Boolean> deathCauses = generateDeathCauses(context.getAnimal());

        deathCauses.keySet()
                .stream()
                .forEach((EventType eventType) -> {
                    if (deathCauses.get(eventType)) {
                        context.getEvents().add(new Event(eventType, context.getAnimal()));
                    }
                });

        context.setDead(deathCauses.containsValue(true));
        return context;
    }

    private Map<EventType, Boolean> generateDeathCauses(Animal animal) {
        Map<EventType, Boolean> deathCauses = new HashMap<>();
        deathCauses.put(EventType.DEATH_OF_AGE, deathPredicates.isDeadByOldAge(animal));
        deathCauses.put(EventType.DEATH_OF_DROWN, deathPredicates.isDeadByDrowning(animal));
        deathCauses.put(EventType.DEAT_OF_HUNGER, deathPredicates.isDeadByHunger(animal));
        return deathCauses;
    }

}
