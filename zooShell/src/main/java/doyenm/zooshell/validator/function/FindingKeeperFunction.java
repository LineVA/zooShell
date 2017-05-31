package doyenm.zooshell.validator.function;

import doyenm.zooshell.validator.context.FindingKeeperContext;
import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class FindingKeeperFunction implements Function<FindingKeeperContext, FindingKeeperContext>{

    @Override
    public FindingKeeperContext apply(FindingKeeperContext t) {
        FindingKeeperContext context = t;
        context.setConvertedKeeper(context.getKeepers().get(context.getKeeper()));
        return context;
    }

}