package doyenm.zooshell.controller.keepercontroller;

import doyenm.zooshell.context.KeeperContext;
import doyenm.zooshell.model.AnimalKeeper;
import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class KeeperDetailsController implements Function<KeeperContext, KeeperContext> {

    @Override
    public KeeperContext apply(KeeperContext t) {
        KeeperContext context = t;
        AnimalKeeper keeper = context.getConvertedKeeper();
        context.addCouple("Name", keeper.getName());
        context.addCouple("Occupations", keeper.getOccupations().toString());
        return context;
    }

}
