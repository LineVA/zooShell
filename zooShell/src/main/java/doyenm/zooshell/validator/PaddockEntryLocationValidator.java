package doyenm.zooshell.validator;

import doyenm.zooshell.context.PaddockEntryCreationContext;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.validator.context.OverlapContext;
import doyenm.zooshell.validator.predicates.CanOverlapPredicate;
import java.util.function.Predicate;
import static java.util.stream.Collectors.toList;
import java.util.stream.Stream;

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
                .filter(new Predicate<Paddock>() {
                    @Override
                    public boolean test(Paddock paddock) {
                        OverlapContext overlap = new OverlapContext(t.getConvertedX(), 0,
                                paddock.getX(), paddock.getWidth());
                        return canOverlapPredicate.test(overlap);
                    }
                })
                // Test voverlap
                .filter(new Predicate<Paddock>() {
                    @Override
                    public boolean test(Paddock paddock) {
                        OverlapContext overlap = new OverlapContext(t.getConvertedY(), 0,
                                paddock.getY(), paddock.getHeight());
                        return canOverlapPredicate.test(overlap);
                    }

                })
                .collect(toList()).isEmpty();
    }

}
