package doyenm.zooshell.validator.function;

import doyenm.zooshell.validator.context.FindingSpecieContext;
import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class FindingSpecieFunction implements Function<FindingSpecieContext, FindingSpecieContext> {

    @Override
    public FindingSpecieContext apply(FindingSpecieContext t) {
        FindingSpecieContext context = t;
        context.setSpecie(context.getSpecies().get(context.getSpecieName().toUpperCase()));
        return context;
    }

}
