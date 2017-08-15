package doyenm.zooshell.controller.animalcontroller.evaluation;

import doyenm.zooshell.context.AnimalEvaluationContext;
import doyenm.zooshell.controller.animalcontroller.evaluation.wellbeing.AnimalBiomeEvaluationController;
import doyenm.zooshell.controller.animalcontroller.evaluation.wellbeing.AnimalDietsEvaluationController;
import doyenm.zooshell.controller.animalcontroller.evaluation.wellbeing.AnimalFastDaysEvaluationController;
import doyenm.zooshell.controller.animalcontroller.evaluation.wellbeing.AnimalFoodQuantityEvaluationController;
import doyenm.zooshell.controller.animalcontroller.evaluation.wellbeing.AnimalGroupSizeEvaluationController;
import doyenm.zooshell.controller.animalcontroller.evaluation.wellbeing.AnimalKeepersTimeInfluenceEvaluationController;
import doyenm.zooshell.controller.animalcontroller.evaluation.wellbeing.AnimalTasksInfluenceEvaluationController;
import doyenm.zooshell.controller.animalcontroller.evaluation.wellbeing.AnimalTerritorySizeEvaluationController;
import java.util.function.Function;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
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
        return Stream.of(context)
                .map(animalBiomeEvaluationController)
                .map(animalDietsEvaluationController)
                .map(animalFastDaysEvaluationController)
                .map(animalFoodQuantityEvaluationController)
                .map(animalGroupSizeEvaluationController)
                .map(animalTerritorySizeEvaluationController)
                .map(animalTasksInfluenceEvaluationController)
                .map(animalKeepersTimeInfluenceEvaluationController)
                .map((AnimalEvaluationContext t1) -> {
                    t1.setWellBeing((t1.getBiomeWellBeing()
                            + t1.getDietsWellBeing()
                            + t1.getFastDaysWellBeing()
                            + t1.getFoodQuantityWellBeing()
                            + t1.getGroupSizeWellBeing()
                            + t1.getTerritorySizeWellBeing()
                            + t1.getTaskInfluenceWellBeing()
                            + t1.getKeeperInfluenceWellBeing())
                            / 8.0);
                    return t1;
                    
                })
                
                .findFirst()
                .get();
    }

}
