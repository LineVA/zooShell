package doyenm.zooshell.common.function;

import doyenm.zooshell.model.Biome;
import doyenm.zooshell.common.context.FindingBiomeContext;

import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class FindingBiomeFunction implements Function<FindingBiomeContext, Biome> {

    ReplaceSpacesWithUnderscores replaceSpacesWithUnderscores = new ReplaceSpacesWithUnderscores();

    @Override
    public Biome apply(FindingBiomeContext t) {
        FindingBiomeContext context = t;
        try {
            int id = Integer.parseInt(context.getBiome());
            for (Biome biome : Biome.values()) {
                if (id == biome.getId()) {
                    context.setConvertedBiome(biome);
                }
            }
        } catch (NumberFormatException ex) {
            String input = replaceSpacesWithUnderscores.replace(context.getBiome());
            for (Biome biome : Biome.values()) {
                if (input.equalsIgnoreCase(biome.name())) {
                    context.setConvertedBiome(biome);
                }
            }
        }
        return context.getConvertedBiome();
    }
}
