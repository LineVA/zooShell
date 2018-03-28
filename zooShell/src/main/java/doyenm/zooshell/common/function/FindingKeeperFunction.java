package doyenm.zooshell.common.function;

import doyenm.zooshell.common.context.FindingKeeperContext;

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