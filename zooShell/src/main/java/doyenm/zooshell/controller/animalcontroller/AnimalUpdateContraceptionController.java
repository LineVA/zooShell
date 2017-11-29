package doyenm.zooshell.controller.animalcontroller;

import doyenm.zooshell.context.AnimalUpdateContraceptionContext;
import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class AnimalUpdateContraceptionController
        implements Function<AnimalUpdateContraceptionContext, AnimalUpdateContraceptionContext> {

    @Override
    public AnimalUpdateContraceptionContext apply(AnimalUpdateContraceptionContext t) {
        AnimalUpdateContraceptionContext context = t;
        context.getConvertedAnimal().setContraceptionMethod(context.getConvertedContraceptionMethod());
        context.getZoo().getAnimals().replace(context.getAnimal(), context.getConvertedAnimal());
        return context;
    }

}
