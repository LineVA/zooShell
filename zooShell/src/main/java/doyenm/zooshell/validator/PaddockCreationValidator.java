package doyenm.zooshell.validator;

import doyenm.zooshell.context.PaddockCreationContext;
import doyenm.zooshell.validator.predicates.IntegerValuePredicates;
import doyenm.zooshell.validator.predicates.StringLengthPredicates;
import doyenm.zooshell.validator.predicates.UniquenessNamesBiPredicates;
import java.util.function.Predicate;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class PaddockCreationValidator
        implements Predicate<PaddockCreationContext> {

    private final StringLengthPredicates stringLengthPredicates;
    private final UniquenessNamesBiPredicates uniquenessNamesBiPredicates;
    private final IntegerValuePredicates integerValuePredicates;

    private final int maxLengthName;
    private final int minHeight;
    private final int minWidth;

    @Override
    public boolean test(PaddockCreationContext t) {
        PaddockCreationContext context = t;
        context.convert();
        boolean result;
        result = this.stringLengthPredicates.mustBeLowerOrEqualsThan(context.getName(), maxLengthName);
        result &= this.uniquenessNamesBiPredicates.test(context.getName(), context.getPaddocksNameSet());
        result &= this.integerValuePredicates.mustBeGreaterOrEqualsThan(context.getConvertedHeight(), minHeight);
        result &= this.integerValuePredicates.mustBeGreaterOrEqualsThan(context.getConvertedWidth(), minWidth);
        return result;
    }
}
