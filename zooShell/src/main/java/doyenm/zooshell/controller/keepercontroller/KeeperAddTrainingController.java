package doyenm.zooshell.controller.keepercontroller;

import doyenm.zooshell.context.KeeperAddTrainingContext;
import doyenm.zooshell.model.Training;
import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class KeeperAddTrainingController 
        implements Function<KeeperAddTrainingContext, KeeperAddTrainingContext>{

    @Override
    public KeeperAddTrainingContext apply(KeeperAddTrainingContext t) {
        t.getZoo().getKeepers().get(t.getKeeperName().toUpperCase()).setTraining(
                Training.builder()
                        .bonus(3.0)
                        .familySubject(t.getFamily())
                        .remainingTurns(3)
                .build()
        );
        return t;
    }
    
}
