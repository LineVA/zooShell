package doyenm.zooshell.controller.paddockcontroller;

import doyenm.zooshell.context.PaddockContext;
import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class PaddockDetailsController implements Function<PaddockContext, PaddockContext> {

    @Override
    public PaddockContext apply(PaddockContext t) {
        PaddockContext context = t;
        context.addCouple("Name", context.getConvertedPaddock().getName());
        context.addCouple("Coordinates", context.getConvertedPaddock().getCoordinates().toString());
//        context.addCouple("Extensions", context.getConvertedPaddock().getExtensions());
        if (context.getConvertedPaddock().getEntry() != null) {
            context.addCouple("Entry", context.getConvertedPaddock().getEntry().toString());
        } else {
            context.addCouple("Entry", "undefined");
        }
        context.addCouple("Biome", context.getConvertedPaddock().getBiome().toString());
        context.addCouple("Type", context.getConvertedPaddock().getType().toString());
        return context;
    }

}
