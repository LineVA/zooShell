package doyenm.zooshell.paddock.facilities;

import java.util.ArrayList;
import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class UpdatePaddockArrangementController
        implements Function<UpdatePaddockArrangementContext, UpdatePaddockArrangementContext> {

    @Override
    public UpdatePaddockArrangementContext apply(UpdatePaddockArrangementContext t) {
        t.getConvertedPaddock().setArrangements(new ArrayList<>());
        t.getConvertedPaddock().getArrangements().addAll(t.difflists());
        t.getZoo().getPaddocks().replace(t.getPaddock(), t.getConvertedPaddock());
        return t;
    }

}
