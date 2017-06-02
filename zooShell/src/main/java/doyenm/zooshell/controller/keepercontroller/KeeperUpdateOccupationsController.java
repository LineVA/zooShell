package doyenm.zooshell.controller.keepercontroller;

import doyenm.zooshell.context.KeeperUpdateOccupationsContext;
import doyenm.zooshell.model.TimedOccupation;
import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class KeeperUpdateOccupationsController
        implements Function<KeeperUpdateOccupationsContext, KeeperUpdateOccupationsContext> {

    @Override
    public KeeperUpdateOccupationsContext apply(KeeperUpdateOccupationsContext t) {
        KeeperUpdateOccupationsContext context = t;
        TimedOccupation timedOccupation = TimedOccupation.builder()
                .paddock(context.getConvertedPaddock())
                .taskType(context.getConvertedTask())
                .time(context.getConvertedTime())
                .build();
        context.getConvertedKeeper().getOccupations().add(timedOccupation);
        return context;
    }

}
