package doyenm.zooshell.controller.animalcontroller;

import doyenm.zooshell.context.AnimalChangeNameContext;
import doyenm.zooshell.model.Animal;
import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class AnimalChangeNameController
        implements Function<AnimalChangeNameContext, AnimalChangeNameContext> {

    @Override
    public AnimalChangeNameContext apply(AnimalChangeNameContext t) {
        AnimalChangeNameContext context = t;
        Animal animal = context.getConvertedAnimal();
        animal.setName(context.getNewName());
        context.getZoo().getAnimals().remove(context.getCurrentName());
        context.getZoo().getAnimals().put(animal.getName(), animal);
        return context;
    }

}
