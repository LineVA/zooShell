package doyenm.zooshell.validator;

import doyenm.zooshell.context.ZooCreationContext;
import doyenm.zooshell.validator.predicates.IntegerValuePredicates;
import doyenm.zooshell.validator.predicates.StringLengthPredicates;
import java.util.function.Predicate;

/**
 *
 * @author doyenm
 */
public class ZooCreationValidator implements Predicate<ZooCreationContext> {

    IntegerValuePredicates integerValuePredicates = new IntegerValuePredicates();
    StringLengthPredicates stringLengthPredicates = new StringLengthPredicates();

    @Override
    public boolean test(ZooCreationContext t) {
    ZooCreationContext context = t;
        context.convert();
        boolean result
                = this.integerValuePredicates.mustBeGreaterOrEqualsThan(context.getConvertedWidth(),10);
        result &= this.integerValuePredicates.mustBeGreaterOrEqualsThan(context.getConvertedHeight(), 10);
        int min = Math.min(context.getConvertedHeight(), context.getConvertedWidth());
        result &= this.integerValuePredicates.mustBeGreaterOrEqualsThan(context.getConvertedHorizon(), 2);
        result &= this.integerValuePredicates.mustBeLowerOrEqualsThan(context.getConvertedHorizon(), min);
        result &= this.integerValuePredicates.mustBeGreaterOrEqualsThan(context.getConvertedSpeed(), 2);
        result &= this.integerValuePredicates.mustBeLowerOrEqualsThan(context.getConvertedSpeed(), 13);
        result &= this.stringLengthPredicates.mustBeLowerOrEqualsThan(context.getName(), 50);
        return result;    
    }
}

//public class ZooCreationValidator implements Function<ZooCreationContext, ZooCreationContext> {
//
//    IntegerValuePredicates integerValuePredicates = new IntegerValuePredicates();
//    StringLengthPredicates stringLengthPredicates = new StringLengthPredicates();
//
//    @Override
//    public ZooCreationContext apply(ZooCreationContext t) {
//        ZooCreationContext context = t;
//        context.convert();
//        boolean result
//                = this.integerValuePredicates.isCorrect(context.getConvertedWidth(), IntegerValuePredicates.mustBeGreaterOrEqualsThan(10));
//        result &= this.integerValuePredicates.isCorrect(context.getConvertedHeight(), IntegerValuePredicates.mustBeGreaterOrEqualsThan(10));
//        int min = Math.min(context.getConvertedHeight(), context.getConvertedWidth());
//        result &= this.integerValuePredicates.isCorrect(context.getConvertedHorizon(), IntegerValuePredicates.mustBeGreaterOrEqualsThan(2));
//        result &= this.integerValuePredicates.isCorrect(context.getConvertedHorizon(), IntegerValuePredicates.mustBeLowerThan(min));
//        result &= this.integerValuePredicates.isCorrect(context.getConvertedSpeed(), IntegerValuePredicates.mustBeGreaterOrEqualsThan(2));
//        result &= this.integerValuePredicates.isCorrect(context.getConvertedSpeed(), IntegerValuePredicates.mustBeLowerThan(13));
//        result &= this.stringLengthPredicates.isCorrect(context.getName(), StringLengthPredicates.mustBeLowerThan(50));
//        return context;
//    }
//}
