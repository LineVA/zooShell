package doyenm.zooshell.controller.animalcontroller.evaluation.wellbeing;

import doyenm.zooshell.context.AnimalEvaluationContext;
import doyenm.zooshell.model.Uicn;
import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class AnimalFoodQuantityEvaluationController
        implements Function<AnimalEvaluationContext, AnimalEvaluationContext> {

    @Override
    public AnimalEvaluationContext apply(AnimalEvaluationContext t) {
        AnimalEvaluationContext context = t;
        double currentDeviation = Utils.computeDeviationBetweenCurrentAndOptimal(
                context.getAnimal().getCurrentFoodAttributes().getQuantity(), 
                context.getAnimal().getOptimalFoodAttributes().getQuantity());
        Uicn uicn= context.getAnimal().getSpecie().getUicn();
        if(Utils.isBetweenAuthorizedValues(currentDeviation, 
                uicn.getStandardDeviation())){
            context.setFoodQuantityWellBeing(context.getBase() * uicn.getCoefficient());
        } else {
            context.setFoodQuantityWellBeing(context.getZero());
        }
        return context;
    }

}
