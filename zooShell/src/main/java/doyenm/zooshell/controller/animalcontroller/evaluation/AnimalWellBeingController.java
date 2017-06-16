package doyenm.zooshell.controller.animalcontroller.evaluation;

import doyenm.zooshell.context.AnimalEvaluationContext;
import doyenm.zooshell.controller.animalcontroller.evaluation.wellbeing.AnimalBiomeEvaluationController;
import doyenm.zooshell.controller.animalcontroller.evaluation.wellbeing.AnimalDietsEvaluationController;
import doyenm.zooshell.controller.animalcontroller.evaluation.wellbeing.AnimalFastDaysEvaluationController;
import doyenm.zooshell.controller.animalcontroller.evaluation.wellbeing.AnimalFoodQuantityEvaluationController;
import doyenm.zooshell.controller.animalcontroller.evaluation.wellbeing.AnimalGroupSizeEvaluationController;
import doyenm.zooshell.controller.animalcontroller.evaluation.wellbeing.AnimalTerritorySizeEvaluationController;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 *
 * @author doyenm
 */
public class AnimalWellBeingController
        implements Function<AnimalEvaluationContext, AnimalEvaluationContext> {

    AnimalBiomeEvaluationController animalBiomeEvaluationController
            = new AnimalBiomeEvaluationController();
    AnimalDietsEvaluationController animalDietsEvaluationController
            = new AnimalDietsEvaluationController();
    AnimalFoodQuantityEvaluationController animalFoodQuantityEvaluationController
            = new AnimalFoodQuantityEvaluationController();
    AnimalFastDaysEvaluationController animalFastDaysEvaluationController
            = new AnimalFastDaysEvaluationController();
    AnimalTerritorySizeEvaluationController animalTerritorySizeEvaluationController
            = new AnimalTerritorySizeEvaluationController();
    AnimalGroupSizeEvaluationController animalGroupSizeEvaluationController
            = new AnimalGroupSizeEvaluationController();

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
                .map((AnimalEvaluationContext t1) -> {
                    t1.setWellBeing((t1.getBiomeWellBeing()
                            + t1.getDietsWellBeing()
                            + t1.getFastDaysWellBeing()
                            + t1.getFoodQuantityWellBeing()
                            + t1.getGroupSizeWellBeing()
                            + t1.getTerritorySizeWellBeing())
                            / 6.0);
                    return t1;
                })
                .findFirst()
                .get();
    }

}
