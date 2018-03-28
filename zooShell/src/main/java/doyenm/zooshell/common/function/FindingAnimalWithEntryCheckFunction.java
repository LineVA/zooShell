package doyenm.zooshell.common.function;

import doyenm.zooshell.model.Animal;
import doyenm.zooshell.common.context.FindingAnimalContext;

import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class FindingAnimalWithEntryCheckFunction implements Function<FindingAnimalContext, FindingAnimalContext> {

    @Override
    public FindingAnimalContext apply(FindingAnimalContext t) {
        FindingAnimalContext context = t;
        Animal animal = context.getAnimals().get(context.getAnimalName().toUpperCase());
        if(animal != null){
            context.setAnimal(animal);
        }
        return context;
    }

}
