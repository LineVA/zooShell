package doyenm.zooshell.controller.animalcontroller;

import doyenm.zooshell.context.AnimalUpdateFastDaysContext;
import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class AnimalUpdateFastDaysController
        implements Function<AnimalUpdateFastDaysContext, AnimalUpdateFastDaysContext> {

    @Override
    public AnimalUpdateFastDaysContext apply(AnimalUpdateFastDaysContext t) {
        t.build();
        return t;
    }

}
