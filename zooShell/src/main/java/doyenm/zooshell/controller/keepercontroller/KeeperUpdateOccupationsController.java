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
        TimedOccupation removingOccupation = null;
        for (TimedOccupation occ : t.getConvertedKeeper().getOccupations()) {
            if (occ.getPaddock().equals(t.getConvertedPaddock()) & occ.getTaskType().equals(t.getConvertedTask())) {
                removingOccupation = occ;
                break;
            }
        }
        if (removingOccupation != null) {
            context.getConvertedKeeper().getOccupations().remove(removingOccupation);
        }
        context.getConvertedKeeper().getOccupations().add(timedOccupation);
        return context;
    }

}
