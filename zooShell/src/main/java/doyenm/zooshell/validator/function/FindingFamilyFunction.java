package doyenm.zooshell.validator.function;

import doyenm.zooshell.model.Family;
import doyenm.zooshell.validator.context.FindingFamilyContext;

import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class FindingFamilyFunction implements Function<FindingFamilyContext, FindingFamilyContext> {

    ReplaceSpacesWithUnderscores replaceSpacesWithUnderscores = new ReplaceSpacesWithUnderscores();

    @Override
    public FindingFamilyContext apply(FindingFamilyContext t) {
        FindingFamilyContext context = t;
        try {
            int id = Integer.parseInt(context.getFamily());
            for (Family family : Family.values()) {
                if (id == family.getId()) {
                    context.setConvertedFamily(family);
                }
            }
        } catch (NumberFormatException ex) {
            String input = replaceSpacesWithUnderscores.replace(context.getFamily());
            for (Family family : Family.values()) {
                if (input.equalsIgnoreCase(family.toString())) {
                    context.setConvertedFamily(family);
                }
            }
        }
        return context;
    }

}
