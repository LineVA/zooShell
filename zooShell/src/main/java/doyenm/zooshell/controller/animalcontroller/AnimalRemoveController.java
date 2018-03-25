package doyenm.zooshell.controller.animalcontroller;

import doyenm.zooshell.context.AnimalContext;

import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class AnimalRemoveController implements Function<AnimalContext, AnimalContext> {

    @Override
    public AnimalContext apply(AnimalContext t) {
        AnimalContext context = t;
        context.getZoo().getAnimals().remove(context.getAnimal().toUpperCase());
        return context;
    }

}
