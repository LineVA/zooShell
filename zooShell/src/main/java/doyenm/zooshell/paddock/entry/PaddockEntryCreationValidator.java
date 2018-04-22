package doyenm.zooshell.paddock.entry;

import doyenm.zooshell.common.FindPaddock;
import doyenm.zooshell.errorhandling.BusinessError;
import doyenm.zooshell.errorhandling.ErrorType;
import doyenm.zooshell.model.Paddock;
import lombok.RequiredArgsConstructor;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class PaddockEntryCreationValidator
        implements Function<PaddockEntryCreationContext, PaddockEntryCreationContext> {

    private final FindPaddock findPaddock;

    @Override
    public PaddockEntryCreationContext apply(PaddockEntryCreationContext t) {
        PaddockEntryCreationContext context = t;
        context.convert();
        retrievePaddock(t);
        return context;
    }

      private PaddockEntryCreationContext retrievePaddock(PaddockEntryCreationContext t) {
       Paddock pad = findPaddock.find(t.getZoo(), t.getPaddock());
        if(pad == null){
            t.getErrors().add(new BusinessError(ErrorType.UNKNOWN_PADDOCK));
        } else {
            t.setConvertedPaddock(pad);
        }
        return t;
    }
}
