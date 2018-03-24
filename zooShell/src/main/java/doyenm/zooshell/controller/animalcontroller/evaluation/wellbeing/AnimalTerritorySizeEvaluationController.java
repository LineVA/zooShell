package doyenm.zooshell.controller.animalcontroller.evaluation.wellbeing;

import doyenm.zooshell.context.AnimalEvaluationContext;
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
                groupsNumber * context.getAnimal().getTerritoryAttributes().getTerritorySizeForOneGroup());
        if (utils.isBetweenAuthorizedValues(currentDeviation,
                context.getUicnStandardDeviation())) {
//            context.setTerritorySizeWellBeing(context.getBase() * context.getUicnCoefficient());
            context.getWellBeingObj().setTerritoryWellBeing(context.BASE * context.getUicnCoefficient());
        } else {
//            context.setTerritorySizeWellBeing(context.getZero());
            context.getWellBeingObj().setTerritoryWellBeing(context.ZERO);
        }
        return context;
    }

}
