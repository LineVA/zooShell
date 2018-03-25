package doyenm.zooshell.validator;

import doyenm.zooshell.context.AnimalUpdateFastDaysContext;
import doyenm.zooshell.validator.predicates.IntegerValuePredicates;
import lombok.RequiredArgsConstructor;

import java.util.function.Predicate;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class AnimalUpdateFastDaysValidator
        implements Predicate<AnimalUpdateFastDaysContext> {

    private final FindAnimal findAnimal;
    private final IntegerValuePredicates integerValuePredicates;

    @Override
    public boolean test(AnimalUpdateFastDaysContext t) {
        t.convert();
        if (!
                (integerValuePredicates.mustBeGreaterOrEqualsThan(t.getConvertedFastDays(), 0)
                &&integerValuePredicates.mustBeLowerOrEqualsThan(t.getConvertedFastDays(), 7))) {
            return false;
        }
        retrieveAnimal(t);
        if (t.getConvertedAnimal() != null) {
            return t.getEntry() != null;
        }
        return false;
    }

    private AnimalUpdateFastDaysContext retrieveAnimal(AnimalUpdateFastDaysContext t) {
        t.setConvertedAnimal(findAnimal.find(t.getZoo(), t.getAnimal()));
        return t;
    }

}
