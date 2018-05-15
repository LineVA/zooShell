package doyenm.zooshell.paddock.facilities;

import doyenm.zooshell.model.PaddockFacility;
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
        t.getConvertedPaddock().setFacilities(Lists.newArrayList(PaddockFacility.NONE));
        return t;
    }

}
