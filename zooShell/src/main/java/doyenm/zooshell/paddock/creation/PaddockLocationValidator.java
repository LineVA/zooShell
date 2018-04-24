package doyenm.zooshell.paddock.creation;

import doyenm.zooshell.common.context.OverlapContext;
import doyenm.zooshell.common.predicates.CanOverlapPredicate;
import doyenm.zooshell.errorhandling.BusinessError;
import doyenm.zooshell.errorhandling.ErrorType;
import doyenm.zooshell.model.Paddock;

import java.util.function.Function;

import static java.util.stream.Collectors.toList;

/**
 * @author doyenm
 */
public class PaddockLocationValidator implements Function<PaddockCreationContext, PaddockCreationContext> {

    CanOverlapPredicate canOverlapPredicate = new CanOverlapPredicate();

    @Override
    public PaddockCreationContext apply(PaddockCreationContext t) {
        boolean overlapWithOtherPad = t.getPaddocksList()
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
                .anyMatch(input -> true);
        if(overlapWithOtherPad){
            t.getErrors().add(new BusinessError(ErrorType.INCORRECT_LOCATION));
        }
        return t;
    }

}
