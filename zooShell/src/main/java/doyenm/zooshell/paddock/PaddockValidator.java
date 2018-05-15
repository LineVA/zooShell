package doyenm.zooshell.paddock;

import doyenm.zooshell.common.FindPaddock;
import doyenm.zooshell.errorhandling.BusinessError;
import doyenm.zooshell.errorhandling.ErrorType;
import doyenm.zooshell.model.Paddock;
import lombok.RequiredArgsConstructor;

import java.util.function.Function;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class PaddockValidator
        implements Function<PaddockContext, PaddockContext> {

    private final FindPaddock findPaddock;

    @Override
    public PaddockContext apply(PaddockContext t) {
        Paddock pad = findPaddock.find(t.getZoo(), t.getPaddock());
        if (pad == null) {
          t.getErrors().add(new BusinessError(ErrorType.UNKNOWN_PADDOCK));
        }
        t.setConvertedPaddock(pad);
        return t;
    }

}
