package doyenm.zooshell.controller.eventhandling.zoo;

import doyenm.zooshell.context.EvaluationContext;

/**
 *
 * @author doyenm
 */
public class KeeperTrainingHandler extends ZooEventsHandler{

    @Override
    public ZooEvent apply(EvaluationContext t) {
        return new ZooEvent(ZooEventType.KEEPER_TRAINING);
    }
}
