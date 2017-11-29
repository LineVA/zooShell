package doyenm.zooshell.validator.predicates;

import java.util.Set;
import java.util.function.BiPredicate;

/**
 *
 * @author doyenm
 */
public class UniquenessNamesBiPredicates implements BiPredicate<String, Set<String>>{


    @Override
    public boolean test(String t, Set<String> u) {
        return !u.contains(t);
    }

}
