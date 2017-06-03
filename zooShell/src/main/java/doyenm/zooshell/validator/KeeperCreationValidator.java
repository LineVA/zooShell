package doyenm.zooshell.validator;

import doyenm.zooshell.context.KeeperCreationContext;
import doyenm.zooshell.validator.predicates.StringLengthPredicates;
import doyenm.zooshell.validator.predicates.UniquenessNamesBiPredicates;
import java.util.function.Predicate;

/**
 *
 * @author doyenm
 */
public class KeeperCreationValidator implements Predicate<KeeperCreationContext> {

    StringLengthPredicates stringLengthPredicates = new StringLengthPredicates();
    UniquenessNamesBiPredicates uniquenessNamesBiPredicates = new UniquenessNamesBiPredicates();

    @Override
    public boolean test(KeeperCreationContext t) {
        KeeperCreationContext context = t;
        boolean result;
        return this.stringLengthPredicates.mustBeLowerOrEqualsThan(context.getKeeper(), 50)
                & this.uniquenessNamesBiPredicates.test(context.getKeeper(), context.getKeepers().keySet());
    }

}
