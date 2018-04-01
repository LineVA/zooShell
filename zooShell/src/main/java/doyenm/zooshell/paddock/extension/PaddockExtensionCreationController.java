package doyenm.zooshell.paddock.extension;

import doyenm.zooshell.paddock.extension.PaddockExtensionCreationContext;

import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class PaddockExtensionCreationController
        implements Function<PaddockExtensionCreationContext, PaddockExtensionCreationContext> {

    @Override
    public PaddockExtensionCreationContext apply(PaddockExtensionCreationContext t) {
        t.build();
        return t;
    }

}
