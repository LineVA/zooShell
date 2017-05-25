package doyenm.zooshell.controller.paddockcontroller;

import doyenm.zooshell.context.PaddockCreationContext;
import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class PaddockCreationController
        implements Function<PaddockCreationContext, PaddockCreationContext> {

    @Override
    public PaddockCreationContext apply(PaddockCreationContext t) {
        t.build();
        return t;
    }

}
