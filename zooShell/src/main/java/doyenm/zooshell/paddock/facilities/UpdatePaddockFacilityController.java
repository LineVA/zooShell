package doyenm.zooshell.paddock.facilities;

import doyenm.zooshell.model.PaddockFacility;

import java.util.ArrayList;
import java.util.Set;
import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class UpdatePaddockFacilityController
        implements Function<UpdatePaddockFacilityContext, UpdatePaddockFacilityContext> {

    @Override
    public UpdatePaddockFacilityContext apply(UpdatePaddockFacilityContext t) {
        Set<PaddockFacility> finalSet = t.difflists();
        t.getConvertedPaddock().setFacilities(new ArrayList<>());
        t.getConvertedPaddock().getFacilities().addAll(finalSet);
        t.getZoo().getPaddocks().replace(t.getPaddock(), t.getConvertedPaddock());
        return t;
    }
}
