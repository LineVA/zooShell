package doyenm.zooshell.validator;

import doyenm.zooshell.context.PaddockContext;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.Paddock;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class PaddockRemoveValidator implements Predicate<PaddockContext> {

    private final FindPaddock findPaddock;

    @Override
    public boolean test(PaddockContext t) {
        PaddockContext context = t;
        Paddock pad = findPaddock.find(context.getZoo(), context.getPaddock().toUpperCase());
        if (pad == null) {
            return false;
        }
        context.setConvertedPaddock(pad);
        // A paddock must be empty of animals to be remove
        return context.getAnimals()
                .stream()
                .filter((Animal t1) -> t1.getPaddock().equals(context.getConvertedPaddock()))
                .collect(Collectors.toList()).isEmpty();
    }

}
