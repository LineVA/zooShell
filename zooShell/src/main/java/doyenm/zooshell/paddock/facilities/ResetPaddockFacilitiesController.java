package doyenm.zooshell.paddock.facilities;

import doyenm.zooshell.model.PaddockArrangement;
import doyenm.zooshell.paddock.PaddockContext;
import org.assertj.core.util.Lists;

import java.util.function.Function;

/**
 * @author doyenm
 */
public class ResetPaddockFacilitiesController
        implements Function<PaddockContext, PaddockContext> {

    @Override
    public PaddockContext apply(PaddockContext t) {
        t.getConvertedPaddock().setArrangements(Lists.newArrayList(PaddockArrangement.NONE));
        return t;
    }

}