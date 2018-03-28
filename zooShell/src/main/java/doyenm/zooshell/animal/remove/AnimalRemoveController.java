package doyenm.zooshell.animal.remove;

import doyenm.zooshell.animal.AnimalContext;

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
