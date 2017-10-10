package doyenm.zooshell.controller.animalcontroller.evaluation.reproduction;

import doyenm.zooshell.context.AnimalEvaluationContext;
import doyenm.zooshell.controller.eventhandling.AnimalEvent;
import doyenm.zooshell.controller.eventhandling.AnimalEventType;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.utils.UniformStatistics;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class ExecuteReproductionFunction
        implements Function<AnimalEvaluationContext, AnimalEvaluationContext> {

    private final CalvingFunction calvingFunction;
    private final UniformStatistics statistics;

    @Override
    public AnimalEvaluationContext apply(AnimalEvaluationContext t) {
        int currentGestationDuration;
        if (t.getCurrentGestationDuration() == 0) {
            int gestationDuringThisTurn = statistics.getRandomLowerOrEqualsThan(t.getMonthsPerTurn());
            currentGestationDuration = gestationDuringThisTurn + t.getCurrentGestationDuration();
        } else {
            currentGestationDuration = t.getMonthsPerTurn() + t.getCurrentGestationDuration();
        }
        if (currentGestationDuration >= t.getGestationDuration()) {
            t.setCurrentGestationDuration(0);
            AnimalEvaluationContext afterCalvingContext = Stream.of(t)
                    .map(calvingFunction)
                    .findFirst()
                    .get();
            afterCalvingContext.getEvents().addAll(generateBirthEvents(afterCalvingContext));
            return afterCalvingContext;
        } else {
            t.setCurrentGestationDuration(currentGestationDuration);
            t.getEvents().addAll(generatePregnancyEvents(t));
            return t;
        }
    }

    private List<AnimalEvent> generateBirthEvents(AnimalEvaluationContext context) {
        List<AnimalEvent> events = new ArrayList<>();
        context.getChildren()
                .stream()
                .forEach((Animal animal) -> {
                    events.add(new AnimalEvent(AnimalEventType.BIRTH, animal, context.getAnimal()));
                });
        return events;
    }

    private List<AnimalEvent> generatePregnancyEvents(AnimalEvaluationContext context) {
        List<AnimalEvent> events = new ArrayList<>();
        if (context.getCurrentGestationDuration()> context.getMonthsPerTurn()) {
            events.add(new AnimalEvent(AnimalEventType.PREGNANCY_PURSUIT, context.getAnimal()));
        } else {
            events.add(new AnimalEvent(AnimalEventType.NEW_PREGNANCY, context.getAnimal()));
        }
        return events;
    }

}
