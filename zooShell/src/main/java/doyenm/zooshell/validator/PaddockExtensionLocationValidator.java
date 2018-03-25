package doyenm.zooshell.validator;

import doyenm.zooshell.context.PaddockExtensionCreationContext;
import doyenm.zooshell.validator.context.OverlapContext;
import doyenm.zooshell.validator.predicates.CanOverlapPredicate;

import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

/**
 *
 * @author doyenm
 */
public class PaddockExtensionLocationValidator implements Predicate<PaddockExtensionCreationContext> {

    CanOverlapPredicate canOverlapPredicate = new CanOverlapPredicate();

    @Override
    public boolean test(PaddockExtensionCreationContext t) {
        return !this.overlapWithOthers(t) && this.overlapWithYourself(t);
    }

    private boolean overlapWithOthers(PaddockExtensionCreationContext t){
        return !t.getCoordinatesListExceptYours()
                .stream()
                // Test hoverlap
                .filter(input -> {
                        OverlapContext overlap = new OverlapContext(t.getConvertedX(), t.getConvertedWidth(),
                                input.getPosition().getX(), input.getWidth());
                        return canOverlapPredicate.test(overlap);
                })
                // Test woverlap
                .filter(input -> {
                        OverlapContext overlap = new OverlapContext(t.getConvertedY(), t.getConvertedHeight(),
                                input.getPosition().getY(), input.getHeight());
                        return canOverlapPredicate.test(overlap);
                })
                .collect(toList()).isEmpty();
    }
    
    private boolean overlapWithYourself(PaddockExtensionCreationContext t){
        return !t.getYourCoordinatesList()
                .stream()
                // Test hoverlap
                .filter(input -> {
                        OverlapContext overlap = new OverlapContext(t.getConvertedX(), t.getConvertedWidth(),
                                input.getPosition().getX(), input.getWidth());
                        return canOverlapPredicate.test(overlap);
                })
                // Test woverlap
                .filter(input -> {
                        OverlapContext overlap = new OverlapContext(t.getConvertedY(), t.getConvertedHeight(),
                                input.getPosition().getY(), input.getHeight());
                        return canOverlapPredicate.test(overlap);
                })
                .collect(toList()).isEmpty();
    }
}
