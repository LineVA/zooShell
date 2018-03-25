package doyenm.zooshell.controller.keepercontroller;

import doyenm.zooshell.context.KeeperContext;

import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class KeeperDeletionController implements Function<KeeperContext, KeeperContext>{

    @Override
    public KeeperContext apply(KeeperContext t) {
        KeeperContext context = t;
        context.getZoo().getKeepers().remove(context.getKeeper());
        return context;
    }

}
