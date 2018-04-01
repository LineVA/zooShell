package doyenm.zooshell.keeper.tasks;

import doyenm.zooshell.keeper.KeeperContext;

import java.util.ArrayList;
import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class KeeperResetOccupationsController
        implements Function<KeeperContext, KeeperContext> {

    @Override
    public KeeperContext apply(KeeperContext t) {
        KeeperContext context = t;
        context.getConvertedKeeper().setOccupations(new ArrayList<>());
        return context;
    }

}
