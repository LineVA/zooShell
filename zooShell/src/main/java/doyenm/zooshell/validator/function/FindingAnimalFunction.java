package doyenm.zooshell.validator.function;

import doyenm.zooshell.validator.context.FindingAnimalContext;
import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class FindingAnimalFunction implements Function<FindingAnimalContext, FindingAnimalContext>{

    @Override
    public FindingAnimalContext apply(FindingAnimalContext t) {
        FindingAnimalContext context = t;
        context.setAnimal(context.getAnimals().get(context.getAnimalName()));
        return context;
    }

}
