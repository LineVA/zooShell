package doyenm.zooshell.animal.list;

import doyenm.zooshell.animal.list.AnimalsWithCriteriaContext;
import doyenm.zooshell.context.LsWithCriteriaContext;
import doyenm.zooshell.animal.list.AnimalsWithDietCriteriaController;
import doyenm.zooshell.animal.list.AnimalsWithPaddockCriteriaController;
import doyenm.zooshell.animal.list.AnimalsWithSexCriteriaController;
import doyenm.zooshell.animal.list.AnimalsWithSpecieCriteriaController;
import doyenm.zooshell.model.Animal;
import lombok.RequiredArgsConstructor;
import org.mvel2.MVEL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class LsAnimalsWithCriteriaController
        implements Function<LsWithCriteriaContext, LsWithCriteriaContext> {

    private final AnimalsWithDietCriteriaController animalsWithDietCriteriaController;
    private final AnimalsWithSexCriteriaController animalsWithSexCriteriaController;
    private final AnimalsWithPaddockCriteriaController animalsWithPaddockCriteriaController;
    private final AnimalsWithSpecieCriteriaController animalsWithSpecieCriteriaController;

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
                        .dietExpressionList(context.getDietsExpression().isEmpty() ? Arrays.asList("true") : context.getDietsExpression())
                        .convertedSexes(context.getConvertedSexes())
                        .sexExpressionList(context.getSexesExpression().isEmpty() ? Arrays.asList("true") : context.getSexesExpression())
                        .convertedPaddocks(context.getConvertedPaddocks())
                        .paddockExpressionList(context.getPaddocksExpression().isEmpty() ? Arrays.asList("true") : context.getPaddocksExpression())
                        .convertedSpecies(context.getConvertedSpecies())
                        .specieExpressionList(context.getSpeciesExpression().isEmpty() ? Arrays.asList("true") : context.getSpeciesExpression())
                        .build())
                .map(animalsWithDietCriteriaController)
                .filter(t1 -> (Boolean) MVEL.eval(t1.getDietExpression()))
                .map(animalsWithSexCriteriaController)
                .filter(t1 -> (Boolean) MVEL.eval(t1.getSexExpression()))
                .map(animalsWithPaddockCriteriaController)
                .filter(t1 -> (Boolean) MVEL.eval(t1.getPaddockExpression()))
                 .map(animalsWithSpecieCriteriaController)
                .filter(t1 -> (Boolean) MVEL.eval(t1.getSpecieExpression()))
                .map(t1 -> t1.getAnimal().getName())
                .collect(Collectors.toList())
        );

        context.getAnimalsList().addAll(correctAnimals);
        return context;
    }
}
