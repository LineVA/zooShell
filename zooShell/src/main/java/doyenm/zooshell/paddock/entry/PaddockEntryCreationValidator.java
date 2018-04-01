package doyenm.zooshell.paddock.entry;

import doyenm.zooshell.common.FindPaddock;
import lombok.RequiredArgsConstructor;

import java.util.function.Predicate;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class PaddockEntryCreationValidator
        implements Predicate<PaddockEntryCreationContext> {

    private final FindPaddock findPaddock;

    @Override
    public boolean test(PaddockEntryCreationContext t) {
        PaddockEntryCreationContext context = t;
        context.convert();
        retrievePaddock(t);
        return context.getConvertedPaddock() != null;
    }

      private PaddockEntryCreationContext retrievePaddock(PaddockEntryCreationContext t) {
        t.setConvertedPaddock(findPaddock.find(t.getZoo(), t.getPaddock()));
        return t;
    }
}
