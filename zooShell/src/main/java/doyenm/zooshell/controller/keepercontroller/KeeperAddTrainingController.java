package doyenm.zooshell.controller.keepercontroller;

import doyenm.zooshell.context.KeeperTrainingContext;
import doyenm.zooshell.controller.eventhandling.zoo.ZooEvent;
import doyenm.zooshell.controller.eventhandling.zoo.ZooEventType;
import doyenm.zooshell.model.Training;
import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class KeeperAddTrainingController implements Function<KeeperTrainingContext, KeeperTrainingContext> {

    @Override
    public KeeperTrainingContext apply(KeeperTrainingContext t) {
        KeeperTrainingContext context = t;
        // Creation of the training
        Training training = Training.builder()
                .bonus(0.5)
                .remainingTurnsNumber(3)
                .subject(context.getConvertedFamily())
                .build();
        // Associate the training to the keeper
        context.getConvertedKeeper().setTraining(training);
        // Update the information directly into the zoo
        context.getZoo().getZooEvents()
                .stream()
                .filter(event -> ZooEventType.KEEPER_TRAINING == event.getEventType())
                .filter(event -> event.getKeeper() == null)
                .forEach(event -> {
                    ZooEvent zooEvent = (ZooEvent) event;
                    zooEvent.setKeeper(context.getConvertedKeeper());
                });
        return context;
    }

}
