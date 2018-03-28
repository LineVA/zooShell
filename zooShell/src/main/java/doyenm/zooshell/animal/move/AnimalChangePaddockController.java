package doyenm.zooshell.animal.move;

import doyenm.zooshell.animal.move.AnimalChangePaddockContext;

import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class AnimalChangePaddockController
        implements Function<AnimalChangePaddockContext, AnimalChangePaddockContext> {

    @Override
    public AnimalChangePaddockContext apply(AnimalChangePaddockContext t) {
        AnimalChangePaddockContext context = t;
        context.getConvertedAnimal().setPaddock(context.getConvertedPaddock());
        context.getZoo().getAnimals().replace(context.getAnimal(), context.getConvertedAnimal());
        return context;
    }

}
