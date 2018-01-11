package doyenm.zooshell.controller.paddockcontroller;

import doyenm.zooshell.context.PaddockContext;
import doyenm.zooshell.utils.Utils;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;

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
//        context.addCouple("Extensions", context.getConvertedPaddock().getExtensions());
        if (context.getConvertedPaddock().getEntry() != null) {
            context.addCouple("Entry", context.getConvertedPaddock().getEntry().toString());
        } else {
            context.addCouple("Entry", "undefined");
        }
        context.addCouple("State", paddockStateFunction.apply(context.getConvertedPaddock().getObsolescence()).toString());
        context.addCouple("Biome", context.getConvertedPaddock().getBiome().toString());
        context.addCouple("Type", context.getConvertedPaddock().getType().toString());
        context.addCouple("Turns in the unsusable state", context.getConvertedPaddock().getTurnsOfUnusableState());
        return context;
    }

}
