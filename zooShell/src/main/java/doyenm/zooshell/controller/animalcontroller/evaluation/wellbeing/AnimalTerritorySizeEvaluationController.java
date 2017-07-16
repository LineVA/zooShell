package doyenm.zooshell.controller.animalcontroller.evaluation.wellbeing;

import doyenm.zooshell.context.AnimalEvaluationContext;
import doyenm.zooshell.model.Uicn;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class AnimalTerritorySizeEvaluationController
        implements Function<AnimalEvaluationContext, AnimalEvaluationContext> {
    
    private final Utils utils;

    @Override
    public AnimalEvaluationContext apply(AnimalEvaluationContext t) {
        AnimalEvaluationContext context = t;
        int territorySize = context.getPaddock().getHeight() * context.getPaddock().getWidth();
        double groupsNumber = (double) context.getNumberOfAnimalsOfTheSameSpecieAndInTheSamePaddock()
                / (double) context.getAnimal().getSocialAttributes().getIndividualsPerGroup();
        double currentDeviation = utils.computeDeviationBetweenCurrentAndOptimal(
              territorySize,
               groupsNumber * context.getSpecie().getTerritoryAttributes().getTerritorySizeForOneGroup());
        Uicn uicn = context.getAnimal().getSpecie().getUicn();
        if (utils.isBetweenAuthorizedValues(currentDeviation,
                uicn.getStandardDeviation())) {
            context.setFoodQuantityWellBeing(context.getBase() * uicn.getCoefficient());
        } else {
            context.setFoodQuantityWellBeing(context.getZero());
        }
        return context;
    }

}
