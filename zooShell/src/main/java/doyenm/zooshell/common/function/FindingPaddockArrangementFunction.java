package doyenm.zooshell.common.function;

import doyenm.zooshell.model.PaddockFacility;
import doyenm.zooshell.common.context.FindingPaddockArrangementContext;
import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class FindingPaddockArrangementFunction
        implements Function<FindingPaddockArrangementContext, PaddockFacility> {

    ReplaceSpacesWithUnderscores replaceSpacesWithUnderscores = new ReplaceSpacesWithUnderscores();

    @Override
    public PaddockFacility apply(FindingPaddockArrangementContext t) {
        FindingPaddockArrangementContext context = t;
        try {
            int id = Integer.parseInt(context.getArrangement());
            for (PaddockFacility arrangement : PaddockFacility.values()) {
                if (id == arrangement.getId()) {
                    context.setConvertedArrangement(arrangement);
                }
            }
        } catch (NumberFormatException ex) {
            String input = replaceSpacesWithUnderscores.replace(context.getArrangement());
            for (PaddockFacility arrangement : PaddockFacility.values()) {
                if (input.equalsIgnoreCase(arrangement.name())) {
                    context.setConvertedArrangement(arrangement);
                }
            }
        }
        return context.getConvertedArrangement();
    }

}
