package doyenm.zooshell.paddock.types;

import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class UpdatePaddockTypeController
        implements Function<UpdatePaddockTypeContext, UpdatePaddockTypeContext> {

    @Override
    public UpdatePaddockTypeContext apply(UpdatePaddockTypeContext t) {
        t.getConvertedPaddock().setType(t.getConvertedType());
        t.getZoo().getPaddocks().replace(t.getPaddock(), t.getConvertedPaddock());
        return t;
    }

}
