package doyenm.zooshell.controller.keepercontroller;

import doyenm.zooshell.context.KeeperContext;
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
