package doyenm.zooshell.keeper.remove;

import doyenm.zooshell.keeper.KeeperContext;

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
