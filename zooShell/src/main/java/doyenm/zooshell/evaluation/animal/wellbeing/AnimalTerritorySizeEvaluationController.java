package doyenm.zooshell.evaluation.animal.wellbeing;

import doyenm.zooshell.evaluation.AnimalEvaluationContext;
import lombok.RequiredArgsConstructor;

import java.util.function.Function;

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
                groupsNumber * context.getAnimal().getTerritoryAttributes().getTerritorySizeForOneGroup());
        if (utils.isBetweenAuthorizedValues(currentDeviation,
                context.getUicnStandardDeviation())) {
            context.getWellBeingObj().setTerritoryWellBeing(AnimalEvaluationContext.BASE * context.getUicnCoefficient());
        } else {
            context.getWellBeingObj().setTerritoryWellBeing(AnimalEvaluationContext.ZERO);
        }
        return context;
    }

}
