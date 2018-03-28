package doyenm.zooshell.keeper.rename;

import doyenm.zooshell.keeper.rename.KeeperRenameContext;
import doyenm.zooshell.model.AnimalKeeper;

import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class KeeperRenameController implements Function<KeeperRenameContext, KeeperRenameContext>{

    @Override
    public KeeperRenameContext apply(KeeperRenameContext t) {
        KeeperRenameContext context = t;
        AnimalKeeper keeper = context.getConvertedKeeper();
        keeper.setName(context.getNewKeeperName());
        context.getZoo().getKeepers().remove(context.getKeeper());
        context.getZoo().getKeepers().put(keeper.getName(), keeper);
        return context;
    }

}
