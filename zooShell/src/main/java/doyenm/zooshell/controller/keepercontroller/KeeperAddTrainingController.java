package doyenm.zooshell.controller.keepercontroller;

import doyenm.zooshell.context.KeeperAddTrainingContext;
import doyenm.zooshell.model.Training;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class KeeperAddTrainingController 
        implements Function<KeeperAddTrainingContext, KeeperAddTrainingContext>{

    private final int duration;
    private final double bonus;
    
    @Override
    public KeeperAddTrainingContext apply(KeeperAddTrainingContext t) {
        t.getZoo().getKeepers().get(t.getKeeperName().toUpperCase()).setTraining(
                Training.builder()
                        .bonus(bonus)
                        .familySubject(t.getFamily())
                        .remainingTurns(duration)
                .build()
        );
        return t;
    }
    
}
