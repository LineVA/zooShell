package doyenm.zooshell.validator;

import doyenm.zooshell.validator.context.FindingPaddockContext;
import doyenm.zooshell.context.PaddockEntryCreationContext;
import doyenm.zooshell.validator.function.FindingPaddockByNameFunction;
import doyenm.zooshell.validator.predicates.IntegerValuePredicates;
import doyenm.zooshell.validator.predicates.StringLengthPredicates;
import doyenm.zooshell.validator.predicates.UniquenessNamesBiPredicates;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 *
 * @author doyenm
 */
public class PaddockEntryCreationValidator
        implements Predicate<PaddockEntryCreationContext> {

    StringLengthPredicates stringLengthPredicates = new StringLengthPredicates();
    UniquenessNamesBiPredicates uniquenessNamesBiPredicates = new UniquenessNamesBiPredicates();
    IntegerValuePredicates integerValuePredicates = new IntegerValuePredicates();
    FindingPaddockByNameFunction findingPaddockByNameFunction = new FindingPaddockByNameFunction();

    @Override
    public boolean test(PaddockEntryCreationContext t) {
        PaddockEntryCreationContext context = t;
        FindingPaddockContext findingContext = new FindingPaddockContext(context.getPaddocksMap(), context.getPaddock());
        context.setConvertedPaddock(Stream.of(findingContext)
                .map(findingPaddockByNameFunction)
                .findFirst()
                .get()
                .getPaddock());
        return context.getConvertedPaddock() != null;
    }

}
