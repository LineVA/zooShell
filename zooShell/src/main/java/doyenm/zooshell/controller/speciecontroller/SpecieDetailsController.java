package doyenm.zooshell.controller.speciecontroller;

import doyenm.zooshell.context.SpecieDetailsContext;
import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class SpecieDetailsController implements Function<SpecieDetailsContext, SpecieDetailsContext> {

    @Override
    public SpecieDetailsContext apply(SpecieDetailsContext t) {
        SpecieDetailsContext context = t;
        context.addCouple("Name", context.getSpecieName());
        context.addCouple("Other names", context.getSpecie().getNames().getAdditionalNames().toString());
        context.addCouple("Scientific name", context.getSpecie().getNames().getScientificName());
        context.addCouple("UICN", context.getSpecie().getUicn().toString());
        context.addCouple("Lifespan attributes", context.getSpecie().getLifespanAttributes().toString());
        context.addCouple("Reproduction attributes", context.getSpecie().getReproductionAttributes().toString());
        context.addCouple("Biomes", context.getSpecie().getBiomes().getBiomes().toString());
        context.addCouple("Diets", context.getSpecie().getDiets().getDiets().toString());
        context.addCouple("Food attributes", context.getSpecie().getFoodAttributes().toString());
        context.addCouple("Social attributes", context.getSpecie().getSocialAttributes().toString());
        context.addCouple("Territiry attributes", context.getSpecie().getTerritoryAttributes().toString());
        return context;
    }

}
