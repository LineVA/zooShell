package doyenm.zooshell.validator.predicates;

/**
 *
 * @author doyenm
 */
public class StringLengthPredicates {
 public boolean mustBeGreaterOrEqualsThan(String testing, int min) {
        return testing.length() >= min;
    }

    public boolean mustBeLowerOrEqualsThan(String testing, int max) {
        return !testing.trim().equals("")  &&  testing.length() <=max;
    }
}