package doyenm.zooshell.validator;

import doyenm.zooshell.context.UpdatePaddockTypeContext;
import doyenm.zooshell.validator.context.FindingPaddockContext;
import doyenm.zooshell.validator.context.FindingPaddockTypeContext;
import doyenm.zooshell.validator.function.FindingPaddockByNameFunction;
import doyenm.zooshell.validator.function.FindingPaddockTypeFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 *
 * @author doyenm
 */
public class UpdatePaddockTypeValidator implements Predicate<UpdatePaddockTypeContext> {

    FindingPaddockByNameFunction findingPaddockByNameFunction = new FindingPaddockByNameFunction();
    FindingPaddockTypeFunction findingPaddockTypeFunction = new FindingPaddockTypeFunction();

    @Override
    public boolean test(UpdatePaddockTypeContext t) {
        FindingPaddockContext findingPaddockContext = new FindingPaddockContext(t.getZoo().getPaddocks(), t.getPaddock());
        FindingPaddockTypeContext findingPaddockTypeContext = new FindingPaddockTypeContext(t.getType());
        t.setConvertedPaddock(Stream.of(findingPaddockContext)
                .map(findingPaddockByNameFunction)
                .findFirst()
                .get()
                .getPaddock());
        t.setConvertedType(Stream.of(findingPaddockTypeContext)
                .map(findingPaddockTypeFunction)
                .findFirst()
                .get()
                .getConvertedType());
        if (t.getConvertedPaddock() != null && t.getConvertedType()!= null) {
            return t.getConvertedPaddock().getEntry() != null;
        }
        return false;
    }
}
