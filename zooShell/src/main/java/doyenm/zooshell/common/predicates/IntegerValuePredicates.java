package doyenm.zooshell.common.predicates;

/**
 *
 * @author doyenm
 */
public class IntegerValuePredicates {

    public boolean mustBeGreaterOrEqualsThan(int testing, int min) {
        return testing >= min;
    }

    public boolean mustBeLowerOrEqualsThan(int testing, int max) {
        return testing <= max;
    }
}
