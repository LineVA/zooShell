package doyenm.zooshell.controller.animalcontroller;

import doyenm.zooshell.context.AnimalsWithCriteriaContext;
import doyenm.zooshell.context.LsWithCriteriaContext;
import doyenm.zooshell.controller.animalcontroller.criteria.AnimalsWithDietCriteriaController;
import doyenm.zooshell.model.Animal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.mvel2.MVEL;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class LsAnimalsWithCriteriaController
        implements Function<LsWithCriteriaContext, LsWithCriteriaContext> {

    private final AnimalsWithDietCriteriaController animalsWithDietCriteriaController;

    @Override
    public LsWithCriteriaContext apply(LsWithCriteriaContext t) {
        LsWithCriteriaContext context = t;
        List<Animal> animals = new ArrayList(context.getZoo().getAnimals().values());
        List<String> correctAnimals = new ArrayList<>();

        correctAnimals.addAll(animals
                .stream()
                .map(animal -> AnimalsWithCriteriaContext.builder()
                        .animal(animal)
                        .convertedDiets(context.getConvertedDiets())
                        .dietExpressionList(context.getDietsExpression())
                        .build())
                .map(animalsWithDietCriteriaController)
                .filter(t1 -> (Boolean) MVEL.eval(t1.getDietExpression()))
                .map(t1 -> t1.getAnimal().getName())
                .collect(Collectors.toList())
        );

        context.getAnimalsList().addAll(correctAnimals);
        return context;
    }
}
