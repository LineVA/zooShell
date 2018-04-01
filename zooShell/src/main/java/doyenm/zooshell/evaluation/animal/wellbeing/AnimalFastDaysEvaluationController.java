package doyenm.zooshell.evaluation.animal.wellbeing;

import doyenm.zooshell.evaluation.AnimalEvaluationContext;

import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class AnimalFastDaysEvaluationController
        implements Function<AnimalEvaluationContext, AnimalEvaluationContext> {

    @Override
    public AnimalEvaluationContext apply(AnimalEvaluationContext t) {
        AnimalEvaluationContext context = t;
        if (context.getAnimal().getCurrentFoodAttributes().getFastDays()
                == context.getAnimal().getOptimalFoodAttributes().getFastDays()) {
            context.getWellBeingObj().setFastDaysWellBeing(AnimalEvaluationContext.BASE);
        } else {
            context.getWellBeingObj().setFastDaysWellBeing(AnimalEvaluationContext.ZERO);
        }
        return context;
    }

}
