package doyenm.zooshell.validator;

import doyenm.zooshell.context.PaddockCreationContext;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.validator.context.OverlapContext;
import doyenm.zooshell.validator.predicates.CanOverlapPredicate;

import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

/**
 *
 * @author doyenm
 */
public class PaddockLocationValidator implements Predicate<PaddockCreationContext> {

    CanOverlapPredicate canOverlapPredicate = new CanOverlapPredicate();

    @Override
    public boolean test(PaddockCreationContext t) {
       return t.getPaddocksList()
                .stream()
                // Test hoverlap
                .filter((Paddock paddock) -> {
                    OverlapContext overlap = new OverlapContext(t.getConvertedX(), t.getConvertedWidth(),
                            paddock.getX(), paddock.getWidth());
                    return canOverlapPredicate.test(overlap);
       })
                // Test woverlap
                .filter((Paddock paddock) -> {
                    OverlapContext overlap = new OverlapContext(t.getConvertedY(), t.getConvertedHeight(),
                            paddock.getY(), paddock.getHeight());
                    return canOverlapPredicate.test(overlap);
       })
                .collect(toList()).isEmpty();
    }

}
