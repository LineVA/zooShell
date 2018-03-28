package doyenm.zooshell.paddock.details;

import doyenm.zooshell.paddock.PaddockContext;
import doyenm.zooshell.utils.Utils;
import lombok.RequiredArgsConstructor;

import java.util.function.Function;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class PaddockDetailsController
        implements Function<PaddockContext, PaddockContext> {

    private final Utils utils = new Utils();
    private final Function paddockStateFunction;

    @Override
    public PaddockContext apply(PaddockContext t) {
        PaddockContext context = t;
        context.addCouple("Name", context.getConvertedPaddock().getName());
        int age = context.getConvertedPaddock().getAge();
        context.addCouple("Age", utils.getNumbersOfYearsFromAge(age) + " year(s), " + utils.getNumbersOfMonthsFromAge(age) + " month(s)");
        context.addCouple("Coordinates", context.getConvertedPaddock().getCoordinates().toString());
        if (context.getConvertedPaddock().getEntry() != null) {
            context.addCouple("Entry", context.getConvertedPaddock().getEntry().toString());
        } else {
            context.addCouple("Entry", "undefined");
        }
        context.addCouple("State", paddockStateFunction.apply(context.getConvertedPaddock().getObsolescence()).toString());
        context.addCouple("Biome", context.getConvertedPaddock().getBiome().toString());
        context.addCouple("Type", context.getConvertedPaddock().getType().toString());
        context.addCouple("Arrangements", context.getConvertedPaddock().getArrangements().toString());
        context.addCouple("Turns in the unsusable state", context.getConvertedPaddock().getTurnsOfUnusableState());
        return context;
    }

}
