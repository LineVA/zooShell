package doyenm.zooshell.controller.animalcontroller.evaluation.wellbeing;

import doyenm.zooshell.context.AnimalEvaluationContext;
import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class AnimalBiomeWBEvaluationController
        implements Function<AnimalEvaluationContext, AnimalEvaluationContext> {

    @Override
    public AnimalEvaluationContext apply(AnimalEvaluationContext t) {
        AnimalEvaluationContext context = t;
        if (context.getAnimal().getSpecie().getBiomes().getBiomes().contains(context.getAnimal().getPaddock().getBiome())) {
            context.setBiomeWellBeing(context.getBase());
        } else {
            context.setBiomeWellBeing(context.getZero());
        }
        return context;
    }

}
