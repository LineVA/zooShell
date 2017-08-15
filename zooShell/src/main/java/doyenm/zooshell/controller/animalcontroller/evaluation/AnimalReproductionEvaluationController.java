package doyenm.zooshell.controller.animalcontroller.evaluation;

import doyenm.zooshell.context.AnimalEvaluationContext;
import doyenm.zooshell.controller.animalcontroller.evaluation.reproduction.ExecuteReproductionFunction;
import doyenm.zooshell.controller.animalcontroller.evaluation.reproduction.FemaleReproductionPredicate;
import doyenm.zooshell.controller.animalcontroller.evaluation.reproduction.MaleReproductionPredicate;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 *
 * @author doyenm
 */
public class AnimalReproductionEvaluationController
        implements Function<AnimalEvaluationContext, AnimalEvaluationContext> {

    FemaleReproductionPredicate femaleReproductionController = new FemaleReproductionPredicate();
    MaleReproductionPredicate maleReproductionController = new MaleReproductionPredicate();
    ExecuteReproductionFunction executeReproductionFunction = new ExecuteReproductionFunction();

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
                return (AnimalEvaluationContext) result.get();
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
