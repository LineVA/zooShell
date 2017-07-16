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
        Uicn uicn = context.getAnimal().getSpecie().getUicn();
        if (utils.isBetweenAuthorizedValues(currentDeviation,
                uicn.getStandardDeviation())) {
            context.setGroupSizeWellBeing(context.getBase() * uicn.getCoefficient());
        } else {
            context.setGroupSizeWellBeing(context.getZero());
        }
        return context;
    }

}
