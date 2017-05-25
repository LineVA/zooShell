package doyenm.zooshell.validator;

import doyenm.zooshell.context.PaddockCreationContext;
import doyenm.zooshell.validator.predicates.IntegerValuePredicates;
import doyenm.zooshell.validator.predicates.StringLengthPredicates;
import doyenm.zooshell.validator.predicates.UniquenessNamesBiPredicates;
import java.util.function.Predicate;

/**
 *
 * @author doyenm
 */
public class PaddockCreationValidator
        implements Predicate<PaddockCreationContext> {

    StringLengthPredicates stringLengthPredicates = new StringLengthPredicates();
    UniquenessNamesBiPredicates uniquenessNamesBiPredicates = new UniquenessNamesBiPredicates();
    IntegerValuePredicates integerValuePredicates = new IntegerValuePredicates();

    @Override
    public boolean test(PaddockCreationContext t) {
        PaddockCreationContext context = t;
        context.convert();
        boolean result;
        result = this.stringLengthPredicates.mustBeLowerOrEqualsThan(context.getName(), 50);
        result &= this.uniquenessNamesBiPredicates.test(context.getName(), context.getZoo().getPaddocks().keySet());
        result &= this.integerValuePredicates.mustBeLowerOrEqualsThan(context.getConvertedWidth(),
                context.getWidthZoo() / 2);
        result &= this.integerValuePredicates.mustBeLowerOrEqualsThan(context.getConvertedHeight(),
                context.getHeightZoo() / 2);
        result &= this.integerValuePredicates.mustBeGreaterOrEqualsThan(context.getConvertedWidth(), 1);
        result &= this.integerValuePredicates.mustBeGreaterOrEqualsThan(context.getConvertedHeight(), 1);
        return result;
    }
}
