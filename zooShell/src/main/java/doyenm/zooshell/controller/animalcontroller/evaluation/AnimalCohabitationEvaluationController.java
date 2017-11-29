package doyenm.zooshell.controller.animalcontroller.evaluation;

import doyenm.zooshell.context.AnimalEvaluationContext;
import doyenm.zooshell.model.utils.CohabitationFactorHandler;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class AnimalCohabitationEvaluationController
        implements Function<AnimalEvaluationContext, AnimalEvaluationContext> {

   private final CohabitationFactorHandler handler;
    
    @Override
    public AnimalEvaluationContext apply(AnimalEvaluationContext t) {
        AnimalEvaluationContext context = t;
        context.getAnimal().getCharacterAttributes().setCohabitationFactor(handler.compute(context.getAnimal()));
        return context;
    }
}
