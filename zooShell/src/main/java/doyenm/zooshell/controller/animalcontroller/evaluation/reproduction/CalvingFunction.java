package doyenm.zooshell.controller.animalcontroller.evaluation.reproduction;

import doyenm.zooshell.context.AnimalCreationContext;
import doyenm.zooshell.context.AnimalEvaluationContext;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.Sex;
import doyenm.zooshell.utils.UniformStatistics;
import doyenm.zooshell.validator.AnimalCreationValidator;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 *
 * @author doyenm
 */
public class CalvingFunction implements Function<AnimalEvaluationContext, AnimalEvaluationContext> {

    UniformStatistics uniformStatistics = new UniformStatistics();
    AnimalCreationValidator animalCreationValidator = new AnimalCreationValidator();

    @Override
    public AnimalEvaluationContext apply(AnimalEvaluationContext t) {
        AnimalEvaluationContext context = t;
        Animal female = context.getAnimal();
        int litterSize = determineLitterSize(context.getAnimal());
        for (int i = 0; i < litterSize; i++) {
            final int j = i + 1;
            context.getChildren().add(
                    Stream.of(context)
                    .map((AnimalEvaluationContext t1) -> new AnimalCreationContext(t1.getZoo(),
                                    determineName(female, j), female.getSpecie().getNames().getName(),
                                    determineSex(), female.getPaddock().getName()))
                    .filter(animalCreationValidator)
                    .map(new Function<AnimalCreationContext, Animal>() {
                        @Override
                        public Animal apply(AnimalCreationContext t) {
                           return t.createNewborn();
                        }
                    })
                    .findFirst()
                    .get()
            );
        }
        female.setNumberOfChildren(female.getNumberOfChildren() + litterSize);
        return context;
    }

    private int determineLitterSize(Animal female) {
        return uniformStatistics.getRandomLowerOrEqualsThan(female.getReproductionAttributes().getLitterSize() * 2);
    }

    private String determineName(Animal female, int j) {
        int number = female.getNumberOfChildren() + j;
        return female.getName() + number;
    }

    private String determineSex() {
        if (uniformStatistics.uniform() >= 0.5) {
            return Sex.MALE.toString();
        }
        return Sex.FEMALE.toString();
    }

}