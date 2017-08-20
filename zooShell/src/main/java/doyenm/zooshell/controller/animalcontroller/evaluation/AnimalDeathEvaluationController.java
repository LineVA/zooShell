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
        Map<EventType, Boolean> events = generateEvents(context.getAnimal());

        events.keySet()
                .stream()
                .forEach((EventType eventType) -> {
                    if (events.get(eventType)) {
                        context.getEvents().add(new Event(eventType, context.getAnimal()));
                    }
                });

        context.setDead(events.containsValue(true));
        return context;
    }

    private Map<EventType, Boolean> generateEvents(Animal animal) {
        Map<EventType, Boolean> events = new HashMap<>();
        events.put(EventType.DEATH_OF_AGE, deathPredicates.isDeadByOldAge(animal));
        events.put(EventType.DEATH_OF_DROWN, deathPredicates.isDeadByDrowning(animal));
        events.put(EventType.DEATH_OF_HUNGER, deathPredicates.isDeadByHunger(animal));
        events.put(EventType.DIYING_OF_DROWN, animal.getDaysOfDrowning() != 0);
        events.put(EventType.DIYING_OF_UNGER, animal.getDaysOfHunger()!= 0);
        return events;
    }

}
