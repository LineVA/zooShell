package doyenm.zooshell.controller.keepercontroller;

import doyenm.zooshell.context.KeeperEvaluationContext;
import doyenm.zooshell.controller.eventhandling.keeper.KeeperEvent;
import doyenm.zooshell.controller.eventhandling.keeper.KeeperEventType;
import doyenm.zooshell.model.AnimalKeeper;
import doyenm.zooshell.model.Training;
import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class KeeperEvaluationTrainingController
        implements Function<KeeperEvaluationContext, KeeperEvaluationContext> {

    @Override
    public KeeperEvaluationContext apply(KeeperEvaluationContext t) {
        AnimalKeeper keeper = t.getKeeper();
        Training training = keeper.getTraining();
        if (training != null) {
            training.setRemainingTurns(training.getRemainingTurns() - 1);
            if (training.getRemainingTurns() <= 0) {
//                keeper.getFamilyEvaluations().replace(
//                        training.getFamilySubject(),
//                        keeper.getFamilyEvaluations().get(training.getFamilySubject()) + training.getBonus());
                keeper = updateEvaluation(keeper);
                keeper.setTraining(null);
                t.getEvents().add(new KeeperEvent(KeeperEventType.END_OF_TRAINING, keeper));
            }
        }
        return t;
    }

    private AnimalKeeper updateEvaluation(AnimalKeeper keeper) {
        Training training = keeper.getTraining();
        if (keeper.getFamilyEvaluations().get(training.getFamilySubject()) != null) {
           keeper.getFamilyEvaluations().replace(
                   training.getFamilySubject(),
                   keeper.getFamilyEvaluations().get(training.getFamilySubject()) + training.getBonus()); 
        } else {
            keeper.getFamilyEvaluations().put(training.getFamilySubject(), training.getBonus());
        }
        return keeper;
    }

}
