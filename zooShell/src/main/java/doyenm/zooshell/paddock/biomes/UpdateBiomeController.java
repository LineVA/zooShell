package doyenm.zooshell.paddock.biomes;

import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class UpdateBiomeController implements Function<UpdateBiomeContext, UpdateBiomeContext>{

    @Override
    public UpdateBiomeContext apply(UpdateBiomeContext t) {
        t.getConvertedPaddock().setBiome(t.getConvertedBiome());
        t.getZoo().getPaddocks().replace(t.getPaddock(), t.getConvertedPaddock());
        return t;
    }

}
