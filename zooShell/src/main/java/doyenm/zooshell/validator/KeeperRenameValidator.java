package doyenm.zooshell.validator;

import doyenm.zooshell.context.KeeperRenameContext;
import doyenm.zooshell.validator.predicates.StringLengthPredicates;
import doyenm.zooshell.validator.predicates.UniquenessNamesBiPredicates;
import java.util.function.Predicate;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class KeeperRenameValidator implements Predicate<KeeperRenameContext> {

    private final StringLengthPredicates stringLengthPredicates;
    private final UniquenessNamesBiPredicates uniquenessNamesBiPredicates;
    private final FindKeeper findKeeper;
    private final int maxLengthName;

    @Override
    public boolean test(KeeperRenameContext t) {
        boolean result;
        result = this.stringLengthPredicates.mustBeLowerOrEqualsThan(t.getNewKeeperName(), maxLengthName);
        result &= this.uniquenessNamesBiPredicates.test(t.getNewKeeperName(), t.getZoo().getKeepers().keySet());
        t.setConvertedKeeper(findKeeper.find(t.getZoo(), t.getKeeper()));
        return result & t.getConvertedKeeper() != null;
    }

}
