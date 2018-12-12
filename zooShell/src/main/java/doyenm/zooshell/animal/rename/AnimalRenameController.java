package doyenm.zooshell.animal.rename;

import doyenm.zooshell.model.Animal;

import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class AnimalRenameController
        implements Function<AnimalRenameContext, AnimalRenameContext> {

    @Override
    public AnimalRenameContext apply(AnimalRenameContext t) {
        AnimalRenameContext context = t;
        Animal animal = context.getConvertedAnimal();
        animal.setName(context.getNewName());
        context.getZoo().getAnimals().remove(context.getCurrentName().toUpperCase());
        context.getZoo().getAnimals().put(animal.getName().toUpperCase(), animal);
        return context;
    }

}
