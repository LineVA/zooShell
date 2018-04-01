package doyenm.zooshell.common.predicates;

/**
 *
 * @author doyenm
 */
public class DoubleValuesPredicates {

    public boolean mustBeGreaterOrEqualsThan(double testing, double min) {
        return testing >= min;
    }

    public boolean mustBeLowerOrEqualsThan(double testing, double max) {
        return testing <= max;
    }
}
