package doyenm.zooshell.validator;

import doyenm.zooshell.context.KeeperRenameContext;
import doyenm.zooshell.validator.predicates.StringLengthPredicates;
import doyenm.zooshell.validator.predicates.UniquenessNamesBiPredicates;
import java.util.function.Predicate;

/**
 *
 * @author doyenm
 */
public class KeeperRenameValidator implements Predicate<KeeperRenameContext> {

    StringLengthPredicates stringLengthPredicates = new StringLengthPredicates();
    UniquenessNamesBiPredicates uniquenessNamesBiPredicates = new UniquenessNamesBiPredicates();
    FindKeeper findKeeper = new FindKeeper();

    @Override
    public boolean test(KeeperRenameContext t) {
        boolean result;
        result = this.stringLengthPredicates.mustBeLowerOrEqualsThan(t.getNewKeeperName(), 50);
        result &= this.uniquenessNamesBiPredicates.test(t.getNewKeeperName(), t.getZoo().getKeepers().keySet());
        t.setConvertedKeeper(findKeeper.find(t.getZoo(), t.getKeeper()));
        return result & t.getConvertedKeeper() != null;
    }

}
