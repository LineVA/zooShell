package doyenm.zooshell.validator;

import doyenm.zooshell.context.ZooCreationContext;
import doyenm.zooshell.validator.predicates.IntegerValuePredicates;
import doyenm.zooshell.validator.predicates.StringLengthPredicates;
import java.util.function.Predicate;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class ZooCreationValidator implements Predicate<ZooCreationContext> {

    IntegerValuePredicates integerValuePredicates = new IntegerValuePredicates();
    StringLengthPredicates stringLengthPredicates = new StringLengthPredicates();

    private final int minWidth;
    private final int minHeight;
    private final int minHorizon;
    private final int minSpeed;
    private final int maxSpeed;
    private final int maxLengthName;

    @Override
    public boolean test(ZooCreationContext t) {
        ZooCreationContext context = t;
        context.convert();
        boolean result
                = this.integerValuePredicates.mustBeGreaterOrEqualsThan(context.getConvertedWidth(), minWidth);
        result &= this.integerValuePredicates.mustBeGreaterOrEqualsThan(context.getConvertedHeight(), minHeight);
        int min = Math.min(context.getConvertedHeight(), context.getConvertedWidth());
        result &= this.integerValuePredicates.mustBeGreaterOrEqualsThan(context.getConvertedHorizon(), minHorizon);
        result &= this.integerValuePredicates.mustBeLowerOrEqualsThan(context.getConvertedHorizon(), min);
        result &= this.integerValuePredicates.mustBeGreaterOrEqualsThan(context.getConvertedSpeed(), minSpeed);
        result &= this.integerValuePredicates.mustBeLowerOrEqualsThan(context.getConvertedSpeed(), maxSpeed);
        result &= this.stringLengthPredicates.mustBeLowerOrEqualsThan(context.getName(), maxLengthName);
        return result;
    }
}
