package doyenm.zooshell.animal;

import doyenm.zooshell.common.function.ReplaceSpacesWithUnderscores;
import doyenm.zooshell.model.Sex;
import doyenm.zooshell.animal.FindingSexContext;

import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class FindingSexFunction implements Function<FindingSexContext, FindingSexContext> {

    ReplaceSpacesWithUnderscores replaceSpacesWithUnderscores = new ReplaceSpacesWithUnderscores();

    @Override
    public FindingSexContext apply(FindingSexContext t) {
        FindingSexContext context = t;
        try {
            int id = Integer.parseInt(context.getSexName());
            for (Sex sex : Sex.values()) {
                if (id == sex.getId()) {
                    context.setSex(sex);
                }
            }
        } catch (NumberFormatException ex) {
            String input = replaceSpacesWithUnderscores.replace(context.getSexName());
            for (Sex sex : Sex.values()) {
                if (input.equalsIgnoreCase(sex.toString())) {
                    context.setSex(sex);
                }
            }
        }
        return context;
    }
}
