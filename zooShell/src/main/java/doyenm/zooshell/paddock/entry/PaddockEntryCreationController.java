package doyenm.zooshell.paddock.entry;

import doyenm.zooshell.paddock.entry.PaddockEntryCreationContext;

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