package doyenm.zooshell.controller.animalcontroller;

import doyenm.zooshell.context.AnimalCreationContext;

import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class AnimalCreationController implements Function<AnimalCreationContext, AnimalCreationContext>{

    @Override
    public AnimalCreationContext apply(AnimalCreationContext t) {
        t.build();
        return t;
    }

}
