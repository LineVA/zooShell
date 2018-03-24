package doyenm.zooshell.controller.animalcontroller.evaluation.wellbeing;

import doyenm.zooshell.context.AnimalEvaluationContext;
import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class AnimalBiomeEvaluationController
        implements Function<AnimalEvaluationContext, AnimalEvaluationContext> {

    @Override
    public AnimalEvaluationContext apply(AnimalEvaluationContext t) {
        AnimalEvaluationContext context = t;
        if (context.getAnimal().getSpecie().getBiomes().getBiomes() == null) {
            context.getWellBeingObj().setBiomeWellBeing(AnimalEvaluationContext.ZERO);
            return context;
        }
        if (context.getAnimal().getSpecie().getBiomes().getBiomes().contains(context.getAnimal().getPaddock().getBiome())) {
            context.getWellBeingObj().setBiomeWellBeing(AnimalEvaluationContext.BASE);

        } else {
            context.setBiomeWellBeing(AnimalEvaluationContext.ZERO);
            context.getWellBeingObj().setBiomeWellBeing(AnimalEvaluationContext.ZERO);

        }
        return context;
    }

}
