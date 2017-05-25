package doyenm.zooshell.validator.function;

import doyenm.zooshell.model.PaddockType;
import doyenm.zooshell.validator.context.FindingPaddockTypeContext;
import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class FindingPaddockTypeFunction implements Function<FindingPaddockTypeContext, FindingPaddockTypeContext> {

    @Override
    public FindingPaddockTypeContext apply(FindingPaddockTypeContext t) {
        FindingPaddockTypeContext context = t;
        try {
            int id = Integer.parseInt(context.getType());
            for (PaddockType type : PaddockType.values()) {
                if (id == type.getId()) {
                    context.setConvertedType(type);
                }
            }
        } catch (NumberFormatException ex) {
            for (PaddockType type : PaddockType.values()) {
                if (context.getType().equalsIgnoreCase(type.name())) {
                    context.setConvertedType(type);
                }
            }
        }
        return context;
    }

}
