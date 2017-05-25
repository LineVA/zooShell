package doyenm.zooshell.controller.animalcontroller.evaluation;

import doyenm.zooshell.context.AnimalEvaluationContext;
import doyenm.zooshell.controller.animalcontroller.evaluation.reproduction.ExecuteReproductionFunction;
import doyenm.zooshell.controller.animalcontroller.evaluation.reproduction.FemaleReproductionPredicate;
import doyenm.zooshell.controller.animalcontroller.evaluation.reproduction.MaleReproductionPredicate;
import doyenm.zooshell.model.Animal;
import java.util.List;
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
        // est une femelle
        // est sexuellement mature
        // a le ben etre necessaire
        // se reproduit

        return Stream.of(context)
                .filter(femaleReproductionController)
                .filter(maleReproductionController)
                .map(executeReproductionFunction)
                .findFirst()
                .get();
        
    }
}
