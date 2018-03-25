package doyenm.zooshell.validator;

import doyenm.zooshell.context.AnimalUpdateFoodQuantityContext;
import doyenm.zooshell.validator.predicates.DoubleValuesPredicates;
import lombok.RequiredArgsConstructor;

import java.util.function.Predicate;

/**
 * @author doyenm
 */
@RequiredArgsConstructor
public class AnimalUpdateFoodQuantityValidator
        implements Predicate<AnimalUpdateFoodQuantityContext> {

    private final DoubleValuesPredicates doubleValuePredicates;
    private final FindAnimal findAnimal;

    @Override
    public boolean test(AnimalUpdateFoodQuantityContext t) {
        t.convert();
        if (!doubleValuePredicates.mustBeGreaterOrEqualsThan(t.getConvertedFoodQuantity(), 0.0)) {
            return false;
        }
        retrieveAnimal(t);
        if (t.getConvertedAnimal() != null) {
            return t.getEntry() != null;
        }
        return false;
    }

    private AnimalUpdateFoodQuantityContext retrieveAnimal(AnimalUpdateFoodQuantityContext t) {
        t.setConvertedAnimal(findAnimal.find(t.getZoo(), t.getAnimal()));
        return t;
    }
}
