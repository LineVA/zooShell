package doyenm.zooshell.validator;

import doyenm.zooshell.context.KeeperRenameContext;
import doyenm.zooshell.validator.context.FindingKeeperContext;
import doyenm.zooshell.validator.function.FindingKeeperFunction;
import doyenm.zooshell.validator.predicates.StringLengthPredicates;
import doyenm.zooshell.validator.predicates.UniquenessNamesBiPredicates;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 *
 * @author doyenm
 */
public class KeeperRenameValidator implements Predicate<KeeperRenameContext> {

    FindingKeeperFunction findingKeeperFunction = new FindingKeeperFunction();
    StringLengthPredicates stringLengthPredicates = new StringLengthPredicates();
    UniquenessNamesBiPredicates uniquenessNamesBiPredicates = new UniquenessNamesBiPredicates();

    @Override
    public boolean test(KeeperRenameContext t) {
        boolean result;
        result = this.stringLengthPredicates.mustBeLowerOrEqualsThan(t.getNewKeeperName(), 50);
        result &= this.uniquenessNamesBiPredicates.test(t.getNewKeeperName(), t.getKeepers().keySet());
        FindingKeeperContext findingKeeperContext = new FindingKeeperContext(t.getKeepers(), t.getNewKeeperName());
        t.setConvertedKeeper(Stream.of(findingKeeperContext)
                .map(findingKeeperFunction)
                .findFirst()
                .get()
                .getConvertedKeeper());
      return result & t.getConvertedKeeper()!= null;
    }
    
}
