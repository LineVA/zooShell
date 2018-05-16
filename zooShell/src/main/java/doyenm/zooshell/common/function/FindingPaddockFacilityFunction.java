package doyenm.zooshell.common.function;

import doyenm.zooshell.model.PaddockFacility;
import doyenm.zooshell.common.context.FindingPaddockFacilityContext;
import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class FindingPaddockFacilityFunction
        implements Function<FindingPaddockFacilityContext, PaddockFacility> {

    ReplaceSpacesWithUnderscores replaceSpacesWithUnderscores = new ReplaceSpacesWithUnderscores();

    @Override
    public PaddockFacility apply(FindingPaddockFacilityContext t) {
        FindingPaddockFacilityContext context = t;
        try {
            int id = Integer.parseInt(context.getFacility());
            for (PaddockFacility facility : PaddockFacility.values()) {
                if (id == facility.getId()) {
                    context.setConvertedFacility(facility);
                }
            }
        } catch (NumberFormatException ex) {
            String input = replaceSpacesWithUnderscores.replace(context.getFacility());
            for (PaddockFacility facility : PaddockFacility.values()) {
                if (input.equalsIgnoreCase(facility.name())) {
                    context.setConvertedFacility(facility);
                }
            }
        }
        return context.getConvertedFacility();
    }

}
