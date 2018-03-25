package doyenm.zooshell.validator;

import doyenm.zooshell.context.PaddockExtensionCreationContext;
import lombok.RequiredArgsConstructor;

import java.util.function.Predicate;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class PaddockExtensionCreationValidator
        implements Predicate<PaddockExtensionCreationContext> {

    private final FindPaddock findPaddock;

    @Override
    public boolean test(PaddockExtensionCreationContext t) {
        t.convert();
        retrievePaddock(t);
        if (t.getConvertedPaddock() != null) {
            return t.getConvertedPaddock().getEntry() != null;
        }
        return false;
    }

    private PaddockExtensionCreationContext retrievePaddock(PaddockExtensionCreationContext t) {
        t.setConvertedPaddock(findPaddock.find(t.getZoo(), t.getPaddock()));
        return t;
    }

}
