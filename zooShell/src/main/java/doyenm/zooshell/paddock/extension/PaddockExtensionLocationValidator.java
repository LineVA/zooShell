package doyenm.zooshell.paddock.extension;

import doyenm.zooshell.common.context.OverlapContext;
import doyenm.zooshell.common.predicates.CanOverlapPredicate;
import doyenm.zooshell.errorhandling.BusinessError;
import doyenm.zooshell.errorhandling.ErrorType;

import java.util.function.Function;

import static java.util.stream.Collectors.toList;

/**
 *
 * @author doyenm
 */
public class PaddockExtensionLocationValidator
        implements Function<PaddockExtensionCreationContext, PaddockExtensionCreationContext> {

    CanOverlapPredicate canOverlapPredicate = new CanOverlapPredicate();

    @Override
    public PaddockExtensionCreationContext apply(PaddockExtensionCreationContext t) {
         if(this.overlapWithOthers(t) || !this.overlapWithYourself(t)){
             t.getErrors().add(new BusinessError(ErrorType.INCORRECT_LOCATION));
         }
         return t;
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
