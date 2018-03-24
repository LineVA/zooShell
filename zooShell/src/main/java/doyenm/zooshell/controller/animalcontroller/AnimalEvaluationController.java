package doyenm.zooshell.controller.animalcontroller;

import doyenm.zooshell.controller.animalcontroller.evaluation.AnimalWellBeingController;
import doyenm.zooshell.controller.animalcontroller.evaluation.AnimalReproductionEvaluationController;
import doyenm.zooshell.controller.animalcontroller.evaluation.AnimalDeathEvaluationController;
import doyenm.zooshell.controller.animalcontroller.evaluation.AnimalAgeEvaluationController;
import doyenm.zooshell.context.AnimalEvaluationContext;
import doyenm.zooshell.context.EvaluationContext;
import doyenm.zooshell.controller.animalcontroller.evaluation.AnimalCohabitationEvaluationController;
import doyenm.zooshell.model.Animal;
import java.util.ArrayList;
import java.util.Collections;
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
    private final AnimalCohabitationEvaluationController animalCohabitationEvaluationController;
    private final AnimalDeathEvaluationController animalDeathEvaluationController;
    private final AnimalReproductionEvaluationController animalReproductionEvaluationController;
    private final AnimalWellBeingController animalWellBeingController;

    @Override
    public EvaluationContext apply(EvaluationContext t) {
        EvaluationContext context = t;
        List<Animal> newborns = new ArrayList<>();
        List<Animal> animals = new ArrayList<>(context.getAnimals().values());
        Collections.shuffle(animals);
        context.setEvaluatedAnimalsList(animals
                .stream()
                .map(t1 -> new AnimalEvaluationContext(context.getZoo(), t1))
                // Reproduction
                .map(animalReproductionEvaluationController)
                .map(t1 -> {
                    newborns.addAll(t1.getChildren());
                    return t1;
                })
                // Age
                .map(animalAgeEvaluationController)
                // Well-being
                .map(animalWellBeingController)
                // Death
                .map(animalDeathEvaluationController)
                .map(t1 -> {
                    context.getAnimalEvents().addAll(t1.getEvents());
                    return t1;
                })
                // Cohabitation
                .map(animalCohabitationEvaluationController)
                .filter(t1 -> !t1.isDead())
                .map(t1 -> {
                    t1.getAnimal().setWellBeingObj(t1.getWellBeingObj());
                    t1.getAnimal().setWellBeing(t1.getAnimal().getWellBeingObj().computeWellBeing());
                    return t1;
                })
                .map(AnimalEvaluationContext::getAnimal)
                .collect(Collectors.toList())
        );
        context.getEvaluatedAnimalsList()
                .stream()
                .forEach(animal ->
                    context.getGradeObj().setAnimalsGrade(context.getGradeObj().getAnimalsGrade() + animal.getWellBeing())
                );
        context.setNewBornsList(newborns);
        return context;
    }

}
