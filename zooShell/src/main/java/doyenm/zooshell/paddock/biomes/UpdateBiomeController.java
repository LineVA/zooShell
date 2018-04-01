package doyenm.zooshell.paddock.biomes;

import doyenm.zooshell.paddock.biomes.UpdateBiomeContext;

import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class UpdateBiomeController implements Function<UpdateBiomeContext, UpdateBiomeContext>{

    @Override
    public UpdateBiomeContext apply(UpdateBiomeContext t) {
        t.build();
        return t;
    }

}
