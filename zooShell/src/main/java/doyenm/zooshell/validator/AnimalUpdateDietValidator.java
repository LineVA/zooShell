package doyenm.zooshell.validator;

import doyenm.zooshell.context.AnimalUpdateDietContext;
import doyenm.zooshell.validator.context.FindingDietContext;
import doyenm.zooshell.validator.function.FindingDietFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class AnimalUpdateDietValidator implements Predicate<AnimalUpdateDietContext> {

    private final FindingDietFunction findingDietFunction;
    private final FindAnimal findAnimal;

    @Override
    public boolean test(AnimalUpdateDietContext t) {
        AnimalUpdateDietContext context = t;
        context = retrieveAnimal(context);
        context = retrieveDiet(context);
        if (t.getConvertedAnimal() != null && t.getConvertedDiets() != null) {
            return t.getConvertedDiets().size() == t.getDiets().size() && t.getEntry() != null;
        }
        return false;
    }

    private AnimalUpdateDietContext retrieveAnimal(AnimalUpdateDietContext t) {
        t.setConvertedAnimal(findAnimal.find(t.getZoo(), t.getAnimal()));
        return t;
    }

    private AnimalUpdateDietContext retrieveDiet(AnimalUpdateDietContext t) {
        if (t.getConvertedDiets() != null) {
            t.getConvertedDiets().addAll(
                    t.getDiets()
                    .stream()
                    .map((String t1) -> new FindingDietContext(t1))
                    .map(findingDietFunction)
                    .map((FindingDietContext t1) -> t1.getConvertedDiet())
                    .filter(e -> e != null)
                    .collect(Collectors.toList())
            );
        }
        return t;
    }
}
