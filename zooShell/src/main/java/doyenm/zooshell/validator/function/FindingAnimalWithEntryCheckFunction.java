package doyenm.zooshell.validator.function;

import doyenm.zooshell.model.Animal;
import doyenm.zooshell.validator.context.FindingAnimalContext;
import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class FindingAnimalWithEntryCheckFunction implements Function<FindingAnimalContext, FindingAnimalContext> {

    @Override
    public FindingAnimalContext apply(FindingAnimalContext t) {
        FindingAnimalContext context = t;
        Animal animal = context.getAnimals().get(context.getAnimalName());
        if(animal != null){
            context.setAnimal(animal);
        }
        return context;
    }

}
