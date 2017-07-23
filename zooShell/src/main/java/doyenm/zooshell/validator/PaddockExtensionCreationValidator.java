package doyenm.zooshell.validator;

import doyenm.zooshell.context.PaddockExtensionCreationContext;
import doyenm.zooshell.validator.context.FindingPaddockContext;
import doyenm.zooshell.validator.function.FindingPaddockByNameFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 *
 * @author doyenm
 */
public class PaddockExtensionCreationValidator
        implements Predicate<PaddockExtensionCreationContext> {

    FindingPaddockByNameFunction findingPaddockByNameFunction = new FindingPaddockByNameFunction();
    
    @Override
    public boolean test(PaddockExtensionCreationContext t) {
        t.convert();
        FindingPaddockContext findingPaddockContext = new FindingPaddockContext(t.getZoo().getPaddocks(), t.getPaddock().toUpperCase());
        t.setConvertedPaddock(Stream.of(findingPaddockContext)
                .map(findingPaddockByNameFunction)
                .findFirst()
                .get()
                .getPaddock());
        if (t.getConvertedPaddock() != null) {
            return t.getConvertedPaddock().getEntry() != null;
        }
        return false;
    }

}
