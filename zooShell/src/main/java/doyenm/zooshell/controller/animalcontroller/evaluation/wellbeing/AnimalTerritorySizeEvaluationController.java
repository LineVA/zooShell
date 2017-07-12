package doyenm.zooshell.controller.animalcontroller.evaluation.wellbeing;

import doyenm.zooshell.context.AnimalEvaluationContext;
import doyenm.zooshell.model.Uicn;
import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class AnimalTerritorySizeEvaluationController
        implements Function<AnimalEvaluationContext, AnimalEvaluationContext> {

    @Override
    public AnimalEvaluationContext apply(AnimalEvaluationContext t) {
        AnimalEvaluationContext context = t;
        int territorySize = context.getPaddock().getHeight() * context.getPaddock().getWidth();
        double groupsNumber = (double) context.getNumberOfAnimalsOfTheSameSpecieAndInTheSamePaddock()
                / (double) context.getAnimal().getSocialAttributes().getIndividualsPerGroup();
        double currentDeviation = Utils.computeDeviationBetweenCurrentAndOptimal(
              territorySize,
               groupsNumber * context.getSpecie().getTerritoryAttributes().getTerritorySizeForOneGroup());
        Uicn uicn = context.getAnimal().getSpecie().getUicn();
        if (Utils.isBetweenAuthorizedValues(currentDeviation,
                uicn.getStandardDeviation())) {
            context.setFoodQuantityWellBeing(context.getBase() * uicn.getCoefficient());
        } else {
            context.setFoodQuantityWellBeing(context.getZero());
        }
        return context;
    }

}
