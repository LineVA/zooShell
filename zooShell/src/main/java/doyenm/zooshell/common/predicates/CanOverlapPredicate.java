package doyenm.zooshell.common.predicates;

import doyenm.zooshell.common.context.OverlapContext;

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
