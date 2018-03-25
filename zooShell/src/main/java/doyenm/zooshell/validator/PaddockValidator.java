package doyenm.zooshell.validator;

import doyenm.zooshell.context.PaddockContext;
import doyenm.zooshell.model.Paddock;
import lombok.RequiredArgsConstructor;

import java.util.function.Predicate;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class PaddockValidator implements Predicate<PaddockContext> {

    private final FindPaddock findPaddock;

    @Override
    public boolean test(PaddockContext t) {
        Paddock pad = findPaddock.find(t.getZoo(), t.getPaddock());
        if (pad == null) {
            return false;
        }
        t.setConvertedPaddock(pad);
        return true;
    }

}
