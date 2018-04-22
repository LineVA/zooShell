package doyenm.zooshell.paddock.entry;

import doyenm.zooshell.model.Position;

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
        Position entry = new Position(t.getConvertedX(), t.getConvertedY());
        t.getConvertedPaddock().setEntry(entry);
        t.getZoo().getPaddocks().replace(t.getPaddock(), t.getConvertedPaddock());
        return context;
    }

}
