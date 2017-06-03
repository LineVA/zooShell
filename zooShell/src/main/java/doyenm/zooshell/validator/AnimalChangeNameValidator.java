package doyenm.zooshell.validator;

import doyenm.zooshell.context.AnimalChangeNameContext;
import doyenm.zooshell.validator.predicates.StringLengthPredicates;
import doyenm.zooshell.validator.predicates.UniquenessNamesBiPredicates;
import java.util.function.Predicate;

/**
 *
 * @author doyenm
 */
public class AnimalChangeNameValidator implements Predicate<AnimalChangeNameContext> {

    FindAnimal findAnimal = new FindAnimal();
    StringLengthPredicates stringLengthPredicates = new StringLengthPredicates();
    UniquenessNamesBiPredicates uniquenessNamesBiPredicates = new UniquenessNamesBiPredicates();

    @Override
    public boolean test(AnimalChangeNameContext t) {
        boolean result;
        result = this.stringLengthPredicates.mustBeLowerOrEqualsThan(t.getNewName(), 50);
        result &= this.uniquenessNamesBiPredicates.test(t.getNewName(), t.getAnimals().keySet());
        t.setConvertedAnimal(findAnimal.find(t.getZoo(), t.getCurrentName()));
        if (result & t.getConvertedAnimal() != null) {
            return t.getConvertedAnimal().getPaddock().getEntry() != null;
        }
        return false;
    }
}
