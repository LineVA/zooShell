package doyenm.zooshell.validator;

import doyenm.zooshell.context.PaddockContext;
import java.util.function.Predicate;

/**
 *
 * @author doyenm
 */
public class PaddockValidator implements Predicate<PaddockContext> {

    FindPaddock findPaddock = new FindPaddock();

    @Override
    public boolean test(PaddockContext t) {
        t.setConvertedPaddock(findPaddock.find(t.getZoo(), t.getPaddock()));
        return t.getConvertedPaddock()!= null;
    }

}
