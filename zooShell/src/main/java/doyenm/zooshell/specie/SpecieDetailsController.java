package doyenm.zooshell.specie;

import doyenm.zooshell.specie.SpecieDetailsContext;
import doyenm.zooshell.model.Specie;

import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class SpecieDetailsController implements Function<SpecieDetailsContext, SpecieDetailsContext> {

    @Override
    public SpecieDetailsContext apply(SpecieDetailsContext t) {
        SpecieDetailsContext context = t;
        Specie specie = context.getSpecie();
        context.addCouple("Name", context.getSpecieName());
        context.addCouple("Other names", specie.getNames().getAdditionalNames().toString());
        context.addCouple("Scientific name", specie.getNames().getScientificName());
        context.addCouple("UICN", specie.getUicn().toString());
        context.addCouple("Family", specie.getFamily().toString());
        context.addCouple("Lifespan attributes", specie.getLifespanAttributes().toString());
        context.addCouple("Reproduction attributes", specie.getReproductionAttributes().toString());
        context.addCouple("Biomes", specie.getBiomes().getBiomes().toString());
        context.addCouple("Arrangements", specie.getArrangements().getArrangements().toString());
        context.addCouple("Diets", specie.getDiets().getDiets().toString());
        context.addCouple("Food attributes", specie.getFoodAttributes().toString());
        context.addCouple("Size", specie.getSizeAttributes().toString());
        context.addCouple("Social attributes", context.getSpecie().getSocialAttributes().toString());
        context.addCouple("Territiry attributes", context.getSpecie().getTerritoryAttributes().toString());
        return context;
    }

}
