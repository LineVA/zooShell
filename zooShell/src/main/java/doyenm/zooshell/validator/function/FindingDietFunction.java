package doyenm.zooshell.validator.function;

import doyenm.zooshell.model.Diet;
import doyenm.zooshell.validator.context.FindingDietContext;
import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class FindingDietFunction implements Function<FindingDietContext, FindingDietContext> {

    ReplaceSpacesWithUnderscores replaceSpacesWithUnderscores = new ReplaceSpacesWithUnderscores();

    @Override
    public FindingDietContext apply(FindingDietContext t) {
        FindingDietContext context = t;
        try {
            int id = Integer.parseInt(context.getDiet());
            for (Diet diet : Diet.values()) {
                if (id == diet.getId()) {
                    context.setConvertedDiet(diet);
                }
            }
        } catch (NumberFormatException ex) {
            String input = replaceSpacesWithUnderscores.replace(context.getDiet());
            for (Diet diet : Diet.values()) {
                if (input.equalsIgnoreCase(diet.name())) {
                    context.setConvertedDiet(diet);
                }
            }
        }
        return context;
    }

}
