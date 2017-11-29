package doyenm.zooshell.validator;

import doyenm.zooshell.context.PaddockEntryCreationContext;
import java.util.function.Predicate;
import lombok.RequiredArgsConstructor;

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
       t = retrievePaddock(t);
        return context.getConvertedPaddock() != null;
    }

      private PaddockEntryCreationContext retrievePaddock(PaddockEntryCreationContext t) {
        t.setConvertedPaddock(findPaddock.find(t.getZoo(), t.getPaddock()));
        return t;
    }
}
