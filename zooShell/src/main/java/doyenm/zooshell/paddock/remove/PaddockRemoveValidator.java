package doyenm.zooshell.paddock.remove;

import doyenm.zooshell.errorhandling.BusinessError;
import doyenm.zooshell.errorhandling.ErrorType;
import doyenm.zooshell.paddock.PaddockContext;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.common.FindPaddock;
import lombok.RequiredArgsConstructor;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author doyenm
 */
@RequiredArgsConstructor
public class PaddockRemoveValidator
        implements Function<PaddockContext, PaddockContext> {

    private final FindPaddock findPaddock;

    @Override
    public PaddockContext apply(PaddockContext t) {
        PaddockContext context = t;
        retrievePaddock(context);
        checkIfPaddockContainsAnimals(context);
        return context;
    }

    private void retrievePaddock(PaddockContext context) {
        Paddock pad = findPaddock.find(context.getZoo(), context.getPaddock().toUpperCase());
        if (pad == null) {
            context.getErrors().add(new BusinessError(ErrorType.UNKNOWN_PADDOCK));
        } else {
            context.setConvertedPaddock(pad);
        }
    }

    private void checkIfPaddockContainsAnimals(PaddockContext context) {
        boolean isContainingAnimals = context.getAnimals()
                .stream()
                .filter((Animal t1) -> t1.getPaddock().equals(context.getConvertedPaddock()))
                .anyMatch(input -> true);
        if(isContainingAnimals){
            context.getErrors().add(new BusinessError(ErrorType.NOT_EMPTY_PADDOCK));
        }

    }

}
