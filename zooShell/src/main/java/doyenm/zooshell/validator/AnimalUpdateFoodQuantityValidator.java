package doyenm.zooshell.validator;

import doyenm.zooshell.context.AnimalUpdateFoodQuantityContext;
import doyenm.zooshell.validator.context.FindingAnimalContext;
import doyenm.zooshell.validator.function.FindingAnimalWithEntryCheckFunction;
import doyenm.zooshell.validator.predicates.DoubleValuesPredicates;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 *
 * @author doyenm
 */
public class AnimalUpdateFoodQuantityValidator
        implements Predicate<AnimalUpdateFoodQuantityContext> {

    FindingAnimalWithEntryCheckFunction findingAnimalFunction = new FindingAnimalWithEntryCheckFunction();
    DoubleValuesPredicates doubleValuePredicates = new DoubleValuesPredicates();

    
    @Override
    public boolean test(AnimalUpdateFoodQuantityContext t) {
        t.convert();
        if(!doubleValuePredicates.mustBeGreaterOrEqualsThan(t.getConvertedFoodQuantity(), 0.0)){
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
