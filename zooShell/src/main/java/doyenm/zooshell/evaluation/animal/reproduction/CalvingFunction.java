package doyenm.zooshell.evaluation.animal.reproduction;

import doyenm.zooshell.animal.creation.AnimalCreationContext;
import doyenm.zooshell.evaluation.AnimalEvaluationContext;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.Sex;
import doyenm.zooshell.utils.UniformStatistics;
import doyenm.zooshell.animal.creation.AnimalCreationValidator;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class CalvingFunction implements Function<AnimalEvaluationContext, AnimalEvaluationContext> {

    private final UniformStatistics uniformStatistics; 
    private final AnimalCreationValidator animalCreationValidator;
    
    private final double percentageOfFemales;
    private final double percentageOfYoungsBreedingByMother;
    private final int maxSizeLitterComparedToTheAnimalValue;

    @Override
    public AnimalEvaluationContext apply(AnimalEvaluationContext t) {
        AnimalEvaluationContext context = t;
        Animal female = context.getAnimal();
        int litterSize = determineLitterSize(context.getAnimal());
        for (int i = 0; i < litterSize; i++) {
            final int j = i + 1;
            Optional optional = Stream.of(context)
                    .map(t1 -> new AnimalCreationContext(t1.getZoo(),
                                    determineName(female, j), female.getSpecie().getNames().getName(),
                                    determineSex(), female.getPaddock().getName()))
                    .filter(animalCreationValidator)
                    .map(AnimalCreationContext::createNewborn)
                    .peek(t1 -> t1.setNotNursingByMother(needWeaningByHumans()))
                    .findFirst();
            if (optional.isPresent()) {
                context.getChildren().add((Animal) optional.get());
            }
        }
        female.setNumberOfChildren(female.getNumberOfChildren() + litterSize);
        return context;
    }

    private int determineLitterSize(Animal female) {
        return uniformStatistics.getRandomLowerOrEqualsThan(
                female.getReproductionAttributes().getLitterSize() * maxSizeLitterComparedToTheAnimalValue);
    }

    private String determineName(Animal female, int j) {
        int number = female.getNumberOfChildren() + j;
        return female.getName() + number;
    }

    private String determineSex() {
        if (uniformStatistics.uniform() >= percentageOfFemales) {
            return Sex.MALE.toString();
        }
        return Sex.FEMALE.toString();
    }

    private boolean needWeaningByHumans() {
        return uniformStatistics.uniform() >= percentageOfYoungsBreedingByMother;
    }

}
