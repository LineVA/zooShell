package doyenm.zooshell.controller.paddockcontroller;

import doyenm.zooshell.context.PaddockEntryCreationContext;
import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class PaddockEntryCreationController
        implements Function<PaddockEntryCreationContext, PaddockEntryCreationContext> {

    @Override
    public PaddockEntryCreationContext apply(PaddockEntryCreationContext t) {
        PaddockEntryCreationContext context = t;
        context.build();
        return context;
    }

}
