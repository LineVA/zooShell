package doyenm.zooshell.controller.animalcontroller.evaluation;

import doyenm.zooshell.context.AnimalEvaluationContext;
import doyenm.zooshell.controller.animalcontroller.evaluation.reproduction.ExecuteReproductionFunction;
import doyenm.zooshell.controller.animalcontroller.evaluation.reproduction.FemaleReproductionPredicate;
import doyenm.zooshell.controller.animalcontroller.evaluation.reproduction.MaleReproductionPredicate;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class AnimalReproductionEvaluationController
        implements Function<AnimalEvaluationContext, AnimalEvaluationContext> {

    private final FemaleReproductionPredicate femaleReproductionController; 
    private final MaleReproductionPredicate maleReproductionController; 
    private final ExecuteReproductionFunction executeReproductionFunction; 

    @Override
    public AnimalEvaluationContext apply(AnimalEvaluationContext t) {
        AnimalEvaluationContext context = t;
        if (context.getAnimal().getMonthsOfGestation() == 0) {
            Optional result = Stream.of(context)
                    .filter(femaleReproductionController)
                    .filter(maleReproductionController)
                    .map(executeReproductionFunction)
                    .findFirst();
            if(result.isPresent()){
                AnimalEvaluationContext actualContext = (AnimalEvaluationContext) result.get();
                actualContext.getAnimal().setMonthsOfGestation(actualContext.getCurrentGestationDuration());
                return actualContext;
            }
            return context;
        } else {
            Optional result = Stream.of(context)
//                    .filter(maleReproductionController)
                    .map(executeReproductionFunction)
                    .findFirst();
              if(result.isPresent()){
                return (AnimalEvaluationContext) result.get();
            }
            return context;
        }

    }
}
