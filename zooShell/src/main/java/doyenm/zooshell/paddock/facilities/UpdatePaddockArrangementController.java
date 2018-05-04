package doyenm.zooshell.paddock.facilities;

import doyenm.zooshell.model.PaddockArrangement;

import java.util.ArrayList;
import java.util.Set;
import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class UpdatePaddockArrangementController
        implements Function<UpdatePaddockArrangementContext, UpdatePaddockArrangementContext> {

    @Override
    public UpdatePaddockArrangementContext apply(UpdatePaddockArrangementContext t) {
        Set<PaddockArrangement> finalSet = t.difflists();
        t.getConvertedPaddock().setArrangements(new ArrayList<>());
        t.getConvertedPaddock().getArrangements().addAll(finalSet);
        t.getZoo().getPaddocks().replace(t.getPaddock(), t.getConvertedPaddock());
        return t;
    }
}
