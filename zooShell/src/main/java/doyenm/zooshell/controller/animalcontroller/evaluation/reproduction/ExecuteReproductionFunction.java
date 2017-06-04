package doyenm.zooshell.controller.animalcontroller.evaluation.reproduction;

import doyenm.zooshell.context.AnimalEvaluationContext;
import doyenm.zooshell.utils.UniformStatistics;
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
        int gestationDurngThisTurn = statistics.getRandomLowerOrEqualsThan(t.getMonthsPerTurn());
        int currentGestationDuration = gestationDurngThisTurn + t.getCurrentGestationDuration();
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
