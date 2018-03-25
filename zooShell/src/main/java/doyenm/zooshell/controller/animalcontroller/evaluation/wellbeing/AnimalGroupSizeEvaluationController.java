package doyenm.zooshell.controller.animalcontroller.evaluation.wellbeing;

import doyenm.zooshell.context.AnimalEvaluationContext;
import lombok.RequiredArgsConstructor;

import java.util.function.Function;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class AnimalGroupSizeEvaluationController
        implements Function<AnimalEvaluationContext, AnimalEvaluationContext> {

    private final Utils utils;

    @Override
    public AnimalEvaluationContext apply(AnimalEvaluationContext t) {
        AnimalEvaluationContext context = t;
        int groupSize = context.getNumberOfAnimalsOfTheSameSpecieAndInTheSamePaddock();
        double currentDeviation = utils.computeDeviationBetweenCurrentAndOptimal(
                groupSize,
                context.getAnimal().getSocialAttributes().getIndividualsPerGroup());
        if (utils.isBetweenAuthorizedValues(currentDeviation,
                context.getUicnStandardDeviation())) {
            context.getWellBeingObj().setSocialWellBeing(AnimalEvaluationContext.BASE * context.getUicnCoefficient());
        } else {
            context.getWellBeingObj().setSocialWellBeing(AnimalEvaluationContext.ZERO);
        }
        return context;
    }

}
