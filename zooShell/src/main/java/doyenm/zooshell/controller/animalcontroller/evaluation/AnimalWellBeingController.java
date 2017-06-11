package doyenm.zooshell.controller.animalcontroller.evaluation;

import doyenm.zooshell.context.AnimalEvaluationContext;
import doyenm.zooshell.controller.animalcontroller.evaluation.wellbeing.AnimalBiomeEvaluationController;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 *
 * @author doyenm
 */
public class AnimalWellBeingController
        implements Function<AnimalEvaluationContext, AnimalEvaluationContext> {

    AnimalBiomeEvaluationController animalBiomeWBEvaluationController
            = new AnimalBiomeEvaluationController();

    @Override
    public AnimalEvaluationContext apply(AnimalEvaluationContext t) {
        AnimalEvaluationContext context = t;
        return Stream.of(context)
                .map(animalBiomeWBEvaluationController)
                .findFirst()
                .get();
    }

}
