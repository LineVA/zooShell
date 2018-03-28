package doyenm.zooshell.validator;

import doyenm.zooshell.context.PaddockEntryCreationContext;
import doyenm.zooshell.common.context.OverlapContext;
import doyenm.zooshell.common.predicates.CanOverlapPredicate;

import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 *
 * @author doyenm
 */
public class PaddockEntryLocationValidator implements Predicate<PaddockEntryCreationContext> {

    CanOverlapPredicate canOverlapPredicate = new CanOverlapPredicate();
    
    @Override
    public boolean test(PaddockEntryCreationContext t) {
          return Stream.of(t.getConvertedPaddock())
                // Test hoverlap
                .filter(input -> {
                        OverlapContext overlap = new OverlapContext(t.getConvertedX(), 0,
                                input.getX(), input.getWidth());
                        return canOverlapPredicate.test(overlap);
                })
                // Test voverlap
                .filter(input ->{
                        OverlapContext overlap = new OverlapContext(t.getConvertedY(), 0,
                                input.getY(), input.getHeight());
                        return canOverlapPredicate.test(overlap);

                })
                .collect(toList()).isEmpty();
    }

}
