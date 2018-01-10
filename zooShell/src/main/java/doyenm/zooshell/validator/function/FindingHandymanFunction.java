package doyenm.zooshell.validator.function;

import doyenm.zooshell.validator.context.FindingHandymanContext;
import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class FindingHandymanFunction implements Function<FindingHandymanContext, FindingHandymanContext>{

    @Override
    public FindingHandymanContext apply(FindingHandymanContext t) {
        FindingHandymanContext context = t;
        context.setHandyman(context.getHandymen().get(context.getHandymanName()));
        return context;
    }

}
