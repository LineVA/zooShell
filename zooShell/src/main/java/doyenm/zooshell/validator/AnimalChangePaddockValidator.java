package doyenm.zooshell.validator;

import doyenm.zooshell.context.AnimalChangePaddockContext;
import doyenm.zooshell.context.AnimalUpdateContraceptionContext;
import doyenm.zooshell.validator.context.FindingAnimalContext;
import doyenm.zooshell.validator.context.FindingPaddockContext;
import doyenm.zooshell.validator.function.FindingAnimalWithEntryCheckFunction;
import doyenm.zooshell.validator.function.FindingPaddockByNameFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 *
 * @author doyenm
 */
public class AnimalChangePaddockValidator implements Predicate<AnimalChangePaddockContext> {

    FindingPaddockByNameFunction findingPaddockByNameFunction = new FindingPaddockByNameFunction();
    FindingAnimalWithEntryCheckFunction findingAnimalFunction = new FindingAnimalWithEntryCheckFunction();

    @Override
    public boolean test(AnimalChangePaddockContext t) {
        t.setConvertedPaddock(Stream.of(new FindingPaddockContext(t.getPaddocksMap(), t.getPaddock()))
                .map(findingPaddockByNameFunction)
                .findFirst()
                .get()
                .getPaddock());
        t.setConvertedAnimal(Stream.of(new FindingAnimalContext(t.getAnimalsMap(), t.getAnimal()))
                .map(findingAnimalFunction)
                .findFirst()
                .get()
                .getAnimal());
        if (t.getConvertedAnimal() != null && t.getConvertedPaddock()!= null) {
            return t.getConvertedAnimal().getPaddock().getEntry() != null;
        }
        return false;
    }

}
