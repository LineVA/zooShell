package doyenm.zooshell.paddock.facilities;

import doyenm.zooshell.paddock.facilities.UpdatePaddockArrangementContext;
import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class UpdatePaddockArrangementController
        implements Function<UpdatePaddockArrangementContext, UpdatePaddockArrangementContext> {

    @Override
    public UpdatePaddockArrangementContext apply(UpdatePaddockArrangementContext t) {
        if (!t.getConvertedPaddock().getArrangements().contains(t.getConvertedArrangement())) {
            t.getConvertedPaddock().getArrangements().add(t.getConvertedArrangement());
            t.getZoo().getPaddocks().replace(t.getPaddock(), t.getConvertedPaddock());
        }
        return t;
    }

}