package doyenm.zooshell.validator;

import doyenm.zooshell.context.PaddockContext;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.Paddock;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 * @author doyenm
 */
public class PaddockRemoveValidator implements Predicate<PaddockContext> {

    private final FindPaddock findPaddock = new FindPaddock();

    @Override
    public boolean test(PaddockContext t) {
        PaddockContext context = t;
        Paddock pad = findPaddock.find(context.getZoo(), t.getPaddock());
        if (pad == null) {
            return false;
        }
        context.setConvertedPaddock(pad);
        boolean isEmpty = context.getAnimals()
                .stream()
                .filter((Animal t1) -> t1.getPaddock().equals(context.getConvertedPaddock()))
                .collect(Collectors.toList()).isEmpty();
        return isEmpty;
    }

}
