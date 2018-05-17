package doyenm.zooshell.paddock;

import doyenm.zooshell.common.function.ReplaceSpacesWithUnderscores;
import doyenm.zooshell.model.PaddockFacility;

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
