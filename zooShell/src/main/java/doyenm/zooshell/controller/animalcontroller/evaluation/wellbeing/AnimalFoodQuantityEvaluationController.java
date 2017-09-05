package doyenm.zooshell.controller.animalcontroller.evaluation.wellbeing;

import doyenm.zooshell.context.AnimalEvaluationContext;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class AnimalFoodQuantityEvaluationController
        implements Function<AnimalEvaluationContext, AnimalEvaluationContext> {

    private final Utils utils;

    @Override
    public AnimalEvaluationContext apply(AnimalEvaluationContext t) {
        AnimalEvaluationContext context = t;
        double currentDeviation = utils.computeDeviationBetweenCurrentAndOptimal(
                context.getAnimal().getCurrentFoodAttributes().getQuantity(),
                context.getAnimal().getOptimalFoodAttributes().getQuantity());
        if (utils.isBetweenAuthorizedValues(currentDeviation,
                context.getUicnStandardDeviation())) {
            context.getWellBeingObj().setFoodQuantityWellBeing(context.getBase() * context.getUicnCoefficient());
//            context.setFoodQuantityWellBeing(context.getBase() * context.getUicnCoefficient());
        } else {
            context.getWellBeingObj().setFoodQuantityWellBeing(context.getZero());
//            context.setFoodQuantityWellBeing(context.getZero());
        }
        return context;
    }

}
