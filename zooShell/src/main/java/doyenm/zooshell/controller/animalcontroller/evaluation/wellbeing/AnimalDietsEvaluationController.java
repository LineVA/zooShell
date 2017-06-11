package doyenm.zooshell.controller.animalcontroller.evaluation.wellbeing;

import doyenm.zooshell.context.AnimalEvaluationContext;
import doyenm.zooshell.model.Diet;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 * @author doyenm
 */
public class AnimalDietsEvaluationController
        implements Function<AnimalEvaluationContext, AnimalEvaluationContext> {

    @Override
    public AnimalEvaluationContext apply(AnimalEvaluationContext t) {
        AnimalEvaluationContext context = t;
        if (isNullOrEmpty(context)) {
            context.setDietsWellBeing(context.getZero());
        } else {
            List<Diet> diets = new ArrayList<>();
            List<Diet> animalDiets = context.getAnimal().getDiets();
            List<Diet> specieDiets = context.getAnimal().getSpecie().getDiets().getDiets();
            diets = addAllIfAbsent(diets, animalDiets);
            diets = addAllIfAbsent(diets, specieDiets);
            int sum = 0;
            for (Diet animalDiet : animalDiets) {
                if (specieDiets.contains(animalDiet)) {
                    sum += 1;
                }
            }
            context.setDietsWellBeing(context.getBase() * (double) sum / ((double) diets.size()));
        }
        return context;
    }

    private boolean isNullOrEmpty(AnimalEvaluationContext context) {
        if (context.getAnimal().getSpecie().getDiets().getDiets() == null
                | context.getAnimal().getDiets() == null) {
            return true;
        } else {
            return context.getAnimal().getDiets().isEmpty()
                    | context.getAnimal().getSpecie().getDiets().getDiets().isEmpty();
        }
    }

    private List<Diet> addAllIfAbsent(List<Diet> currentList, List<Diet> addingList) {
        List<Diet> diets = currentList;
        diets.addAll(addingList
                .stream()
                .filter((Diet t) -> !diets.contains(t))
                .collect(Collectors.toList())
        );
        return diets;
    }

}