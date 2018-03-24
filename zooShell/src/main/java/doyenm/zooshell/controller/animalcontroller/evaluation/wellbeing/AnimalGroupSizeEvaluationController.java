package doyenm.zooshell.controller.animalcontroller.evaluation.wellbeing;

import doyenm.zooshell.context.AnimalEvaluationContext;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;

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
//            context.setGroupSizeWellBeing(context.getBase() * context.getUicnCoefficient());
            context.getWellBeingObj().setSocialWellBeing(context.BASE * context.getUicnCoefficient());
        } else {
//            context.setGroupSizeWellBeing(context.getZero());
            context.getWellBeingObj().setSocialWellBeing(context.ZERO);
        }
        return context;
    }

}
