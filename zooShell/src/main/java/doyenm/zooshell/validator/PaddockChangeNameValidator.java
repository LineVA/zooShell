package doyenm.zooshell.validator;

import doyenm.zooshell.context.PaddockChangeNameContext;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.validator.predicates.StringLengthPredicates;
import doyenm.zooshell.validator.predicates.UniquenessNamesBiPredicates;
import java.util.function.Predicate;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class PaddockChangeNameValidator
        implements Predicate<PaddockChangeNameContext> {

    private final FindPaddock findPaddock;
    private final StringLengthPredicates stringLengthPredicates;
    private final UniquenessNamesBiPredicates uniquenessNamesBiPredicates;
    private final int maxLengthName;

    @Override
    public boolean test(PaddockChangeNameContext t) {
        PaddockChangeNameContext context = t;
        Paddock pad = findPaddock.find(context.getZoo(), context.getCurrentName());
        if (pad == null) {
            return false;
        }
        context.setConvertedPaddock(pad);
        return this.stringLengthPredicates.mustBeLowerOrEqualsThan(context.getNewName(), maxLengthName)
                & this.uniquenessNamesBiPredicates.test(context.getNewName(), context.getPaddocks());
    }
}
