package doyenm.zooshell.validator;

import doyenm.zooshell.context.AnimalUpdateDietContext;
import doyenm.zooshell.validator.context.FindingAnimalContext;
import doyenm.zooshell.validator.context.FindingDietContext;
import doyenm.zooshell.validator.function.FindingAnimalFunction;
import doyenm.zooshell.validator.function.FindingDietFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author doyenm
 */
public class AnimalUpdateDietValidator implements Predicate<AnimalUpdateDietContext> {

    FindingDietFunction findingDietFunction = new FindingDietFunction();
    FindingAnimalFunction findingAnimalFunction = new FindingAnimalFunction();

    @Override
    public boolean test(AnimalUpdateDietContext t) {
        FindingAnimalContext findingAnimalContext = new FindingAnimalContext(t.getZoo().getAnimals(), t.getAnimal());
        t.setConvertedAnimal(Stream.of(findingAnimalContext)
                .map(findingAnimalFunction)
                .findFirst()
                .get()
                .getAnimal());
        t.getConvertedDiets().addAll(
                t.getDiets()
                .stream()
                .map((String t1) -> new FindingDietContext(t1))
                .map(findingDietFunction)
                .map((FindingDietContext t1) -> t1.getConvertedDiet())
                .collect(Collectors.toList())
        );
        if (t.getConvertedAnimal() != null && t.getConvertedDiets() != null) {
            return t.getConvertedDiets().size() == t.getDiets().size() && t.getConvertedAnimal().getPaddock().getEntry() != null;
        }
        return false;
    }
}
