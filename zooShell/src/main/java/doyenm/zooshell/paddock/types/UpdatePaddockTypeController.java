package doyenm.zooshell.paddock.types;

import doyenm.zooshell.paddock.types.UpdatePaddockTypeContext;

import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class UpdatePaddockTypeController
        implements Function<UpdatePaddockTypeContext, UpdatePaddockTypeContext> {

    @Override
    public UpdatePaddockTypeContext apply(UpdatePaddockTypeContext t) {
        t.build();
        return t;
    }

}
