package doyenm.zooshell.common.function;

import doyenm.zooshell.model.PaddockArrangement;
import doyenm.zooshell.common.context.FindingPaddockArrangementContext;
import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class FindingPaddockArrangementFunction
        implements Function<FindingPaddockArrangementContext, FindingPaddockArrangementContext> {

    ReplaceSpacesWithUnderscores replaceSpacesWithUnderscores = new ReplaceSpacesWithUnderscores();

    @Override
    public FindingPaddockArrangementContext apply(FindingPaddockArrangementContext t) {
        FindingPaddockArrangementContext context = t;
        try {
            int id = Integer.parseInt(context.getArrangement());
            for (PaddockArrangement arrangement : PaddockArrangement.values()) {
                if (id == arrangement.getId()) {
                    context.setConvertedArrangement(arrangement);
                }
            }
        } catch (NumberFormatException ex) {
            String input = replaceSpacesWithUnderscores.replace(context.getArrangement());
            for (PaddockArrangement arrangement : PaddockArrangement.values()) {
                if (input.equalsIgnoreCase(arrangement.name())) {
                    context.setConvertedArrangement(arrangement);
                }
            }
        }
        return context;
    }

}
