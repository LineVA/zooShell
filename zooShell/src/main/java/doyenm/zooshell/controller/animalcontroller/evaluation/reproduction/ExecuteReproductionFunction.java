package doyenm.zooshell.controller.animalcontroller.evaluation.reproduction;

import doyenm.zooshell.context.AnimalEvaluationContext;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 *
 * @author doyenm
 */
public class ExecuteReproductionFunction
        implements Function<AnimalEvaluationContext, AnimalEvaluationContext> {

    private CalvingFunction calvingFunction;

    public ExecuteReproductionFunction() {
        this.calvingFunction = new CalvingFunction();
    }

    public ExecuteReproductionFunction(CalvingFunction calvingFunction) {
        this.calvingFunction = calvingFunction;
    }

    @Override
    public AnimalEvaluationContext apply(AnimalEvaluationContext t) {
        int currentGestationDuration = t.getMonthsPerTurn() + t.getCurrentGestationDuration();
        if (currentGestationDuration >= t.getGestationDuration()) {
            t.setCurrentGestationDuration(0);
            return Stream.of(t)
                    .map(calvingFunction)
                    .findFirst()
                    .get();
        } else {
            t.setCurrentGestationDuration(currentGestationDuration);
            return t;
        }
    }

}
