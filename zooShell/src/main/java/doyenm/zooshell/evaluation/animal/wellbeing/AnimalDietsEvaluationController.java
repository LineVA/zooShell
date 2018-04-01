package doyenm.zooshell.evaluation.animal.wellbeing;

import doyenm.zooshell.evaluation.AnimalEvaluationContext;
import doyenm.zooshell.model.Diet;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author doyenm
 */
public class AnimalDietsEvaluationController
        implements Function<AnimalEvaluationContext, AnimalEvaluationContext> {

    @Override
    public AnimalEvaluationContext apply(AnimalEvaluationContext t) {
        AnimalEvaluationContext context = t;
        if (isNullOrEmpty(context)) {
            context.setDietsWellBeing(AnimalEvaluationContext.ZERO);
        } else {
            List<Diet> diets = new ArrayList<>();
            List<Diet> animalDiets = context.getAnimal().getDiets();
            List<Diet> specieDiets = context.getAnimal().getSpecie().getDiets().getDiets();
            addAllIfAbsent(diets, animalDiets);
            addAllIfAbsent(diets, specieDiets);
            int sum = 0;
            for (Diet animalDiet : animalDiets) {
                if (specieDiets.contains(animalDiet)) {
                    sum += 1;
                }
            }
            context.getWellBeingObj().setDietsWellBeing(AnimalEvaluationContext.BASE * (double) sum / ((double) diets.size()));
        }
        return context;
    }

    private boolean isNullOrEmpty(AnimalEvaluationContext context) {
        if (context.getAnimal().getSpecie().getDiets().getDiets() == null
                || context.getAnimal().getDiets() == null) {
            return true;
        } else {
            return context.getAnimal().getDiets().isEmpty()
                    || context.getAnimal().getSpecie().getDiets().getDiets().isEmpty();
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
