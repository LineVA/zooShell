package doyenm.zooshell.controller.animalcontroller.evaluation.wellbeing;

import doyenm.zooshell.context.AnimalEvaluationContext;
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
        if (context.getAnimal().getCurrentFoodAttributes().getFastDays() ==
                context.getAnimal().getOptimalFoodAttributes().getFastDays()) {
            context.setFastDaysWellBeing(context.getBase());
        } else {
            context.setFastDaysWellBeing(context.getZero());
        }
        return context;
    }

}