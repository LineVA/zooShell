package doyenm.zooshell.controller.animalcontroller.evaluation.reproduction;

import doyenm.zooshell.context.AnimalEvaluationContext;
import doyenm.zooshell.controller.eventhandling.Event;
import doyenm.zooshell.controller.eventhandling.EventType;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.utils.UniformStatistics;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 *
 * @author doyenm
 */
public class ExecuteReproductionFunction
        implements Function<AnimalEvaluationContext, AnimalEvaluationContext> {

    private CalvingFunction calvingFunction;
    private UniformStatistics statistics;

    public ExecuteReproductionFunction() {
        this.calvingFunction = new CalvingFunction();
        this.statistics = new UniformStatistics();
    }

    public ExecuteReproductionFunction(CalvingFunction calvingFunction, UniformStatistics stat) {
        this.calvingFunction = calvingFunction;
        this.statistics = stat;
    }

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
    
    private List<Event> generateBirthEvents(AnimalEvaluationContext context){
        List<Event> events = new ArrayList<>();
        context.getChildren()
                .stream()
                .forEach((Animal animal) -> {
                    events.add(new Event(EventType.BIRTH, animal, context.getAnimal()));
                });
        return events;
    }

      private List<Event> generatePregnancyEvents(AnimalEvaluationContext context){
        List<Event> events = new ArrayList<>();
        events.add(new Event(EventType.PREGNANCY, context.getAnimal()));
        return events;
    }
    
}
