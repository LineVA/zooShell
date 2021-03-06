package doyenm.zooshell.validator.function;

import doyenm.zooshell.model.Biome;
import doyenm.zooshell.validator.context.FindingBiomeContext;
import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class FindingBiomeFunction implements Function<FindingBiomeContext, FindingBiomeContext> {


    @Override
    public FindingBiomeContext apply(FindingBiomeContext t) {
        FindingBiomeContext context = t;
        try {
            int id = Integer.parseInt(context.getBiome());
            for (Biome biome : Biome.values()) {
                if (id == biome.getId()) {
                    context.setConvertedBiome(biome);
                }
            }
        } catch (NumberFormatException ex) {
            for (Biome biome : Biome.values()) {
                if (context.getBiome().equalsIgnoreCase(biome.name())) {
                    context.setConvertedBiome(biome);
                }
            }
        }
        return context;
    }
}
