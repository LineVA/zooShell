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
            for (PaddockFacility arrangement : PaddockFacility.values()) {
                if (id == arrangement.getId()) {
                    context.setConvertedFacility(arrangement);
                }
            }
        } catch (NumberFormatException ex) {
            String input = replaceSpacesWithUnderscores.replace(context.getFacility());
            for (PaddockFacility arrangement : PaddockFacility.values()) {
                if (input.equalsIgnoreCase(arrangement.name())) {
                    context.setConvertedFacility(arrangement);
                }
            }
        }
        return context.getConvertedFacility();
    }

}
