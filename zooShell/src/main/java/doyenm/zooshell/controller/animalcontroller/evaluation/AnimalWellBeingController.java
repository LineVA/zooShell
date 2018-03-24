package doyenm.zooshell.controller.animalcontroller.evaluation;

import doyenm.zooshell.context.AnimalEvaluationContext;
import doyenm.zooshell.controller.animalcontroller.evaluation.wellbeing.*;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author doyenm
 */
@Slf4j
@RequiredArgsConstructor
public class AnimalWellBeingController
        implements Function<AnimalEvaluationContext, AnimalEvaluationContext> {

    private final AnimalBiomeEvaluationController animalBiomeEvaluationController;
    private final AnimalDietsEvaluationController animalDietsEvaluationController;
    private final AnimalFoodQuantityEvaluationController animalFoodQuantityEvaluationController;
    private final AnimalFastDaysEvaluationController animalFastDaysEvaluationController;
    private final AnimalTerritorySizeEvaluationController animalTerritorySizeEvaluationController;
    private final AnimalGroupSizeEvaluationController animalGroupSizeEvaluationController;
    private final AnimalTasksInfluenceEvaluationController animalTasksInfluenceEvaluationController;
    private final AnimalKeepersTimeInfluenceEvaluationController animalKeepersTimeInfluenceEvaluationController;

    @Override
    public AnimalEvaluationContext apply(AnimalEvaluationContext t) {
        AnimalEvaluationContext context = t;
        Optional<AnimalEvaluationContext> optional = Stream.of(context)
                .map(animalBiomeEvaluationController)
                .map(animalDietsEvaluationController)
                .map(animalFastDaysEvaluationController)
                .map(animalFoodQuantityEvaluationController)
                .map(animalGroupSizeEvaluationController)
                .map(animalTerritorySizeEvaluationController)
                .map(animalTasksInfluenceEvaluationController)
                .map(animalKeepersTimeInfluenceEvaluationController)
                .findFirst();
        if (optional.isPresent()) {
            return optional.get();
        } else {
            log.info("Fatal Error : no value returning from the animals evaluation controller's");
            return context;
        }
    }
}
