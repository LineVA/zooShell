package doyenm.zooshell.evaluation.animal;

import doyenm.zooshell.evaluation.AnimalEvaluationContext;
import doyenm.zooshell.evaluation.animal.reproduction.ExecuteReproductionFunction;
import doyenm.zooshell.evaluation.animal.reproduction.FemaleReproductionPredicate;
import doyenm.zooshell.evaluation.animal.reproduction.MaleReproductionPredicate;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

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
        // If not currently in gestation
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
