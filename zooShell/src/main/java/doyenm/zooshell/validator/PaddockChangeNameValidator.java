package doyenm.zooshell.validator;

import doyenm.zooshell.context.PaddockChangeNameContext;
import doyenm.zooshell.validator.context.FindingPaddockContext;
import doyenm.zooshell.validator.function.FindingPaddockByNameFunction;
import doyenm.zooshell.validator.predicates.StringLengthPredicates;
import doyenm.zooshell.validator.predicates.UniquenessNamesBiPredicates;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 *
 * @author doyenm
 */
public class PaddockChangeNameValidator
        implements Predicate<PaddockChangeNameContext> {

    FindingPaddockByNameFunction findingPaddockFunction = new FindingPaddockByNameFunction();
    StringLengthPredicates stringLengthPredicates = new StringLengthPredicates();
    UniquenessNamesBiPredicates uniquenessNamesBiPredicates = new UniquenessNamesBiPredicates();

    @Override
    public boolean test(PaddockChangeNameContext t) {
        boolean result;
        result = this.stringLengthPredicates.mustBeLowerOrEqualsThan(t.getNewName(), 50);
        result &= this.uniquenessNamesBiPredicates.test(t.getNewName(), t.getPaddocks().keySet());
        FindingPaddockContext findingContext = new FindingPaddockContext(t.getZoo().getPaddocks(), t.getCurrentName());
        t.setConvertedPaddock(Stream.of(findingContext)
                .map(findingPaddockFunction)
                .findFirst()
                .get()
                .getPaddock());
        if(result & t.getConvertedPaddock()!= null){
            return t.getConvertedPaddock().getEntry() != null;
        }
        return false;
    }
}
