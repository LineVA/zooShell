package doyenm.zooshell.validator;

import doyenm.zooshell.context.AnimalUpdateFastDaysContext;
import doyenm.zooshell.validator.context.FindingAnimalContext;
import doyenm.zooshell.validator.function.FindingAnimalWithEntryCheckFunction;
import doyenm.zooshell.validator.predicates.DoubleValuesPredicates;
import doyenm.zooshell.validator.predicates.IntegerValuePredicates;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 *
 * @author doyenm
 */
public class AnimalUpdateFastDaysValidator
        implements Predicate<AnimalUpdateFastDaysContext> {

    FindingAnimalWithEntryCheckFunction findingAnimalFunction = new FindingAnimalWithEntryCheckFunction();
    DoubleValuesPredicates doubleValuePredicates = new DoubleValuesPredicates();
    IntegerValuePredicates integerValuePredicates = new IntegerValuePredicates();

    @Override
    public boolean test(AnimalUpdateFastDaysContext t) {
        t.convert();
        if (!integerValuePredicates.mustBeGreaterOrEqualsThan(t.getConvertedFastDays(), 0)
                || !integerValuePredicates.mustBeLowerOrEqualsThan(t.getConvertedFastDays(), 7)) {
            return false;
        }
        FindingAnimalContext findingAnimalContext = new FindingAnimalContext(t.getZoo().getAnimals(), t.getAnimal());
        t.setConvertedAnimal(Stream.of(findingAnimalContext)
                .map(findingAnimalFunction)
                .findFirst()
                .get()
                .getAnimal());
        if (t.getConvertedAnimal() != null) {
            return t.getConvertedAnimal().getPaddock().getEntry() != null;
        }
        return false;
    }

}
