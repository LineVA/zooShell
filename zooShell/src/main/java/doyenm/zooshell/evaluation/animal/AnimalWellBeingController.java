package doyenm.zooshell.evaluation.animal;

import doyenm.zooshell.evaluation.AnimalEvaluationContext;
import doyenm.zooshell.evaluation.animal.wellbeing.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

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
    private final AnimalArrangementsEvaluationController animalArrangementsEvaluationController;

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
                .map(animalArrangementsEvaluationController)
                .findFirst();
        if (optional.isPresent()) {
            return optional.get();
        } else {
            log.info("Fatal Error : no value returning from the animals animal controller's");
            return context;
        }
    }
}
