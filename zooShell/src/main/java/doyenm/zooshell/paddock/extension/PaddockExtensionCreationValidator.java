package doyenm.zooshell.paddock.extension;

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
public class PaddockExtensionCreationValidator
        implements Function<PaddockExtensionCreationContext, PaddockExtensionCreationContext> {

    private final FindPaddock findPaddock;

    @Override
    public PaddockExtensionCreationContext apply(PaddockExtensionCreationContext t) {
        t.convert();
        retrievePaddock(t);
        return t;
    }

    private PaddockExtensionCreationContext retrievePaddock(PaddockExtensionCreationContext t) {
        Paddock pad = findPaddock.find(t.getZoo(), t.getPaddock());
        if(pad == null){
            t.getErrors().add(new BusinessError(ErrorType.UNKNOWN_PADDOCK));
        } else {
            if(pad.getEntry() == null){
                t.getErrors().add(new BusinessError((ErrorType.NO_ENTRY)));
            }
            t.setConvertedPaddock(pad);
        }
        return t;
    }

}
