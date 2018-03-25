package doyenm.zooshell.controller.paddockcontroller;

import doyenm.zooshell.context.UpdatePaddockTypeContext;

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
