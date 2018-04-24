package doyenm.zooshell.paddock.rename;

import doyenm.zooshell.model.Paddock;

import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class PaddockChangeNameController
        implements Function<PaddockChangeNameContext, PaddockChangeNameContext> {

    @Override
    public PaddockChangeNameContext apply(PaddockChangeNameContext t) {
        PaddockChangeNameContext context = t;
        Paddock pad = context.getConvertedPaddock();
        pad.setName(context.getNewName());
        context.getZoo().getPaddocks().remove(context.getCurrentName().toUpperCase());
        context.getZoo().getPaddocks().put(pad.getName().toUpperCase(), pad);
        return context;
    }

}
