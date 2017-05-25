package doyenm.zooshell.controller.animalcontroller;

import doyenm.zooshell.context.AnimalUpdateDietContext;
import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class AnimalUpdateDietController
        implements Function<AnimalUpdateDietContext, AnimalUpdateDietContext> {

    @Override
    public AnimalUpdateDietContext apply(AnimalUpdateDietContext t) {
        t.build();
        return t;
    }

}
