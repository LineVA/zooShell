package doyenm.zooshell.common.function;

import doyenm.zooshell.common.context.FindingPaddockContext;

import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class FindingPaddockByNameFunction
        implements Function<FindingPaddockContext, FindingPaddockContext> {

    @Override
    public FindingPaddockContext apply(FindingPaddockContext t) {
        FindingPaddockContext context = t;
        context.setPaddock(context.getPaddocks().get(context.getPaddockName().toUpperCase()));
        return context;
    }

}
