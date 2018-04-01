package doyenm.zooshell.keeper.training;

import doyenm.zooshell.keeper.training.KeeperAddTrainingContext;
import doyenm.zooshell.model.Training;
import lombok.RequiredArgsConstructor;

import java.util.function.Function;

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
