package doyenm.zooshell.controller.animalcontroller.evaluation;

import doyenm.zooshell.context.AnimalEvaluationContext;
import doyenm.zooshell.controller.animalcontroller.evaluation.death.AnimalDeathPredicates;
import doyenm.zooshell.controller.animalcontroller.evaluation.death.AnimalUpdateDyingMeasures;
import doyenm.zooshell.controller.eventhandling.animal.AnimalEvent;
import doyenm.zooshell.controller.eventhandling.animal.AnimalEventCategory;
import doyenm.zooshell.controller.eventhandling.animal.AnimalEventType;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.Penalty;
import doyenm.zooshell.model.PenaltyType;
import lombok.RequiredArgsConstructor;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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
        Map<AnimalEventType, Boolean> events = generateEvents(context);

        events.keySet()
                .stream()
                .forEach((AnimalEventType eventType) -> {
                    if (events.get(eventType)) {
                        if (context.getAnimal().getKiller() != null) {
                            context.getEvents().add(new AnimalEvent(eventType, context.getAnimal(), context.getAnimal().getKiller()));
                        } else {
                            context.getEvents().add(new AnimalEvent(eventType, context.getAnimal()));
                        }
                    }
                });

        context.setDead(isDead(events));
        context.getZoo().getPenalties().addAll(addPenalties(context));

        return filterDeadEvents(context);
    }

    private List<Penalty> addPenalties(AnimalEvaluationContext context) {
        return context.getEvents()
                .stream()
                .filter(event -> AnimalEventType.DEATH_OF_AGE.getAvoidableDeathsEventTypes().contains(event.getEventType()))
                .map(event -> Penalty.builder()
                .remainingTurns(3)
                .value(3.0)
                .type(PenaltyType.AVOIDABLE_ANIMAL_DEATH)
                .build()
                )
                .collect(Collectors.toList());
    }

    /**
     * If the animal is dead, we delete the events about this animal dying
     *
     * @param context
     * @return
     */
    private AnimalEvaluationContext filterDeadEvents(AnimalEvaluationContext context) {
        if (context.isDead()) {
            context.setEvents(context.getEvents()
                    .stream()
                    .filter(event -> event.getEventType().getAnimalEventCategory() == AnimalEventCategory.DEAD)
                    .collect(Collectors.toList())
            );
        }
        return context;
    }

    private Map<AnimalEventType, Boolean> generateEvents(AnimalEvaluationContext context) {
        Animal animal = context.getAnimal();
        Map<AnimalEventType, Boolean> events = new EnumMap<>(AnimalEventType.class);
        events.put(AnimalEventType.DEATH_OF_AGE, deathPredicates.isDeadByOldAge(animal, context.getMaxWellBeingWithoutKeeper()));
        events.put(AnimalEventType.DEATH_OF_DROWN, deathPredicates.isDeadByDrowning(animal));
        events.put(AnimalEventType.DEATH_OF_HUNGER, deathPredicates.isDeadByHunger(animal));
        events.put(AnimalEventType.DEATH_OF_PREDATION, deathPredicates.isDeadByPredation(animal));
        events.put(AnimalEventType.DIYING_OF_DROWN, animal.getDaysOfDrowning() != 0);
        events.put(AnimalEventType.DIYING_OF_HUNGER, animal.getDaysOfHunger() != 0);
        return events;
    }

    private boolean isDead(Map<AnimalEventType, Boolean> events) {
        return events.get(AnimalEventType.DEATH_OF_AGE)
                || events.get(AnimalEventType.DEATH_OF_DROWN)
                || events.get(AnimalEventType.DEATH_OF_HUNGER)
                || events.get(AnimalEventType.DEATH_OF_PREDATION);
    }

}
