package doyenm.zooshell.common.function;

import doyenm.zooshell.model.PaddockType;
import doyenm.zooshell.common.context.FindingPaddockTypeContext;

import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class FindingPaddockTypeFunction implements Function<FindingPaddockTypeContext, PaddockType> {

    ReplaceSpacesWithUnderscores replaceSpacesWithUnderscores = new ReplaceSpacesWithUnderscores();

    @Override
    public PaddockType apply(FindingPaddockTypeContext t) {
        FindingPaddockTypeContext context = t;
        try {
            int id = Integer.parseInt(context.getType());
            for (PaddockType type : PaddockType.values()) {
                if (id == type.getId()) {
                    context.setConvertedType(type);
                }
            }
        } catch (NumberFormatException ex) {
            String input = replaceSpacesWithUnderscores.replace(context.getType());
            for (PaddockType type : PaddockType.values()) {
                if (input.equalsIgnoreCase(type.name())) {
                    context.setConvertedType(type);
                }
            }
        }
        return context.getConvertedType();
    }

}
