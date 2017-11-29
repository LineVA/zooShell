package doyenm.zooshell.validator;

import doyenm.zooshell.context.AnimalUpdateFastDaysContext;
import doyenm.zooshell.validator.predicates.IntegerValuePredicates;
import java.util.function.Predicate;
import lombok.RequiredArgsConstructor;

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
        t = retrieveAnimal(t);
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
