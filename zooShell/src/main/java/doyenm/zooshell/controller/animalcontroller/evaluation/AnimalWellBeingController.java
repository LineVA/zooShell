package doyenm.zooshell.controller.animalcontroller.evaluation;

import doyenm.zooshell.context.AnimalEvaluationContext;
import doyenm.zooshell.controller.animalcontroller.evaluation.wellbeing.AnimalBiomeWBEvaluationController;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 *
 * @author doyenm
 */
public class AnimalWellBeingController
        implements Function<AnimalEvaluationContext, AnimalEvaluationContext> {

    AnimalBiomeWBEvaluationController animalBiomeWBEvaluationController
            = new AnimalBiomeWBEvaluationController();

    @Override
    public AnimalEvaluationContext apply(AnimalEvaluationContext t) {
        AnimalEvaluationContext context = t;
        return Stream.of(context)
                .map(animalBiomeWBEvaluationController)
                .findFirst()
                .get();
    }

}
