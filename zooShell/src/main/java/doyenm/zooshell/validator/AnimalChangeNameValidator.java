package doyenm.zooshell.validator;

import doyenm.zooshell.context.AnimalChangeNameContext;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.validator.predicates.StringLengthPredicates;
import doyenm.zooshell.validator.predicates.UniquenessNamesBiPredicates;
import java.util.function.Predicate;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class AnimalChangeNameValidator implements Predicate<AnimalChangeNameContext> {

    private final FindAnimal findAnimal;
    private final StringLengthPredicates stringLengthPredicates;
    private final UniquenessNamesBiPredicates uniquenessNamesBiPredicates;
    private final int maxLengthName;

    @Override
    public boolean test(AnimalChangeNameContext t) {
        AnimalChangeNameContext context = t;
        Animal animal = findAnimal.find(context.getZoo(), context.getCurrentName());
        if (animal == null) {
            return false;
        }
        context.setConvertedAnimal(animal);
        return this.stringLengthPredicates.mustBeLowerOrEqualsThan(context.getNewName(), maxLengthName)
                & this.uniquenessNamesBiPredicates.test(context.getNewName().toUpperCase(), context.getAnimals());
    }
}
