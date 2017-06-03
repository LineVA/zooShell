package doyenm.zooshell.controller.paddockcontroller;

import doyenm.zooshell.context.PaddockContext;
import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class PaddockRemoveController implements Function<PaddockContext, PaddockContext>{

    @Override
    public PaddockContext apply(PaddockContext t) {
        PaddockContext context = t;
        context.getZoo().getPaddocks().remove(context.getPaddock());
        return context;
    }

}
