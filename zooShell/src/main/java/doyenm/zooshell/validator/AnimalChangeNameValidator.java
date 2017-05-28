package doyenm.zooshell.validator;

import doyenm.zooshell.context.AnimalChangeNameContext;
import doyenm.zooshell.validator.context.FindingAnimalContext;
import doyenm.zooshell.validator.function.FindingAnimalFunction;
import doyenm.zooshell.validator.predicates.StringLengthPredicates;
import doyenm.zooshell.validator.predicates.UniquenessNamesBiPredicates;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 *
 * @author doyenm
 */
public class AnimalChangeNameValidator implements Predicate<AnimalChangeNameContext> {

    FindingAnimalFunction findingAnimalFunction = new FindingAnimalFunction();
    StringLengthPredicates stringLengthPredicates = new StringLengthPredicates();
    UniquenessNamesBiPredicates uniquenessNamesBiPredicates = new UniquenessNamesBiPredicates();

    @Override
    public boolean test(AnimalChangeNameContext t) {
        boolean result;
        result = this.stringLengthPredicates.mustBeLowerOrEqualsThan(t.getCurrentName(), 50);
        result &= this.uniquenessNamesBiPredicates.test(t.getNewName(), t.getAnimals().keySet());
        FindingAnimalContext findingAnimalContext = new FindingAnimalContext(t.getZoo().getAnimals(), t.getCurrentName());
        t.setConvertedAnimal(Stream.of(findingAnimalContext)
                .map(findingAnimalFunction)
                .findFirst()
                .get()
                .getAnimal());
        if (result & t.getConvertedAnimal() != null) {
            return t.getConvertedAnimal().getPaddock().getEntry() != null;
        }
        return false;
    }
}
