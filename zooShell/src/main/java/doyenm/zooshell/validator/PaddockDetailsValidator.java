package doyenm.zooshell.validator;

import doyenm.zooshell.context.PaddockDetailsContext;
import doyenm.zooshell.validator.context.FindingPaddockContext;
import doyenm.zooshell.validator.function.FindingPaddockByNameFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 *
 * @author doyenm
 */
public class PaddockDetailsValidator implements Predicate<PaddockDetailsContext> {

    FindingPaddockByNameFunction findingPaddockByNameFunction = new FindingPaddockByNameFunction();

    @Override
    public boolean test(PaddockDetailsContext t) {
        FindingPaddockContext findingPaddockContext = new FindingPaddockContext(t.getZoo().getPaddocks(), t.getPaddock());

        t.setConvertedPaddock(Stream.of(findingPaddockContext)
                .map(findingPaddockByNameFunction)
                .findFirst()
                .get()
                .getPaddock());
       return t.getConvertedPaddock() != null;
    }

}
