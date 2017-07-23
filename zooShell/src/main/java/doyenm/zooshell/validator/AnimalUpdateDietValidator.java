package doyenm.zooshell.validator;

import doyenm.zooshell.context.AnimalUpdateDietContext;
import doyenm.zooshell.validator.context.FindingAnimalContext;
import doyenm.zooshell.validator.context.FindingDietContext;
import doyenm.zooshell.validator.function.FindingAnimalWithEntryCheckFunction;
import doyenm.zooshell.validator.function.FindingDietFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class AnimalUpdateDietValidator implements Predicate<AnimalUpdateDietContext> {

    private final FindingDietFunction findingDietFunction;
    private final FindingAnimalWithEntryCheckFunction findingAnimalFunction; 

    @Override
    public boolean test(AnimalUpdateDietContext t) {
        FindingAnimalContext findingAnimalContext = new FindingAnimalContext(t.getZoo().getAnimals(), t.getAnimal().toUpperCase());
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
                .filter(e -> e != null)
                .collect(Collectors.toList())
        );
        if (t.getConvertedAnimal() != null && t.getConvertedDiets() != null) {
            return t.getConvertedDiets().size() == t.getDiets().size() && t.getConvertedAnimal().getPaddock().getEntry() != null;
        }
        return false;
    }
}
