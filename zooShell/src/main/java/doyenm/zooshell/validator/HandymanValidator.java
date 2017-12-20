package doyenm.zooshell.validator;

import doyenm.zooshell.context.HandymanContext;
import java.util.function.Predicate;

/**
 *
 * @author doyenm
 */
public class HandymanValidator implements Predicate<HandymanContext> {

    FindHandyman findHandyman = new FindHandyman();

    @Override
    public boolean test(HandymanContext t) {
        t.setHandyman(findHandyman.find(t.getZoo(), t.getHandymanName()));
        return t.getHandyman()!= null;
    }
}
