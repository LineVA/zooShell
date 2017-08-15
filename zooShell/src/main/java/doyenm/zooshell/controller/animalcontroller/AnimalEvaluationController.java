package doyenm.zooshell.controller.animalcontroller;

import doyenm.zooshell.controller.animalcontroller.evaluation.AnimalWellBeingController;
import doyenm.zooshell.controller.animalcontroller.evaluation.AnimalReproductionEvaluationController;
import doyenm.zooshell.controller.animalcontroller.evaluation.AnimalDeathEvaluationController;
import doyenm.zooshell.controller.animalcontroller.evaluation.AnimalAgeEvaluationController;
import doyenm.zooshell.context.AnimalEvaluationContext;
import doyenm.zooshell.context.EvaluationContext;
import doyenm.zooshell.controller.animalcontroller.evaluation.wellbeing.AnimalTasksInfluenceEvaluationController;
import doyenm.zooshell.model.Animal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class AnimalEvaluationController implements Function<EvaluationContext, EvaluationContext> {

    private final AnimalAgeEvaluationController animalAgeEvaluationController;
    private final AnimalDeathEvaluationController animalDeathEvaluationController;
    private final AnimalReproductionEvaluationController animalReproductionEvaluationController;
    private final AnimalWellBeingController animalWellBeingController;
    

    @Override
    public EvaluationContext apply(EvaluationContext t) {
        EvaluationContext context = t;
        List<Animal> newborns = new ArrayList<>();
        context.setEvaluatedAnimalsList(context.getAnimals().values()
                .stream()
                .map((Animal t1) -> new AnimalEvaluationContext(context.getZoo(), t1))
                // Reproduction
                .map(animalReproductionEvaluationController)
                .map((AnimalEvaluationContext t1) -> {
                    newborns.addAll(t1.getChildren());
                    return t1;
                })
                // Age
                .map(animalAgeEvaluationController)
                // Well-being
                .map(animalWellBeingController)
                // Death
                .map(animalDeathEvaluationController)
                .filter((AnimalEvaluationContext t1) -> !t1.isDead())
                .map((AnimalEvaluationContext t1) ->{
                    t1.getAnimal().setWellBeing(t1.getWellBeing());
                    return t1;
                })
                .map((AnimalEvaluationContext t1) -> t1.getAnimal())
                .collect(Collectors.toList())
        );
        context.setNewBornsList(newborns);
        return context;
    }

}
