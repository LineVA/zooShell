package doyenm.zooshell.validator.predicates;

import doyenm.zooshell.validator.context.OverlapContext;

/**
 *
 * @author doyenm
 */
public class CanOverlapPredicate {

    public boolean test(OverlapContext t) {
        return (t.getFirstValue() <= t.getSecondValue() + t.getSecondSize())
        && (t.getSecondValue() <= t.getFirstValue() + t.getFirstSize());
    }

}
