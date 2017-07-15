package doyenm.zooshell.controller.animalcontroller.evaluation.wellbeing;

import doyenm.zooshell.context.AnimalEvaluationContext;
import doyenm.zooshell.model.Uicn;
import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class AnimalGroupSizeEvaluationController
        implements Function<AnimalEvaluationContext, AnimalEvaluationContext> {

    @Override
    public AnimalEvaluationContext apply(AnimalEvaluationContext t) {
        AnimalEvaluationContext context = t;
        int groupSize = context.getNumberOfAnimalsOfTheSameSpecieAndInTheSamePaddock();
        double currentDeviation = Utils.computeDeviationBetweenCurrentAndOptimal(
                groupSize,
                context.getAnimal().getSocialAttributes().getIndividualsPerGroup());
        Uicn uicn = context.getAnimal().getSpecie().getUicn();
        if (Utils.isBetweenAuthorizedValues(currentDeviation,
                uicn.getStandardDeviation())) {
            context.setGroupSizeWellBeing(context.getBase() * uicn.getCoefficient());
        } else {
            context.setGroupSizeWellBeing(context.getZero());
        }
        return context;
    }

}
