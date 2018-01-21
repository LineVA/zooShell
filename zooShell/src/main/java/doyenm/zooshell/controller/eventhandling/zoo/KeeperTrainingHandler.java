package doyenm.zooshell.controller.eventhandling.zoo;

import doyenm.zooshell.context.EvaluationContext;

/**
 *
 * @author doyenm
 */
public class KeeperTrainingHandler implements ZooEventsHandler{

    @Override
    public boolean canExecute(EvaluationContext t) {
        return true;
    }

    @Override
    public ZooEvent execute(EvaluationContext t) {
        return new ZooEvent(ZooEventType.KEEPER_TRAINING);
    }
}
