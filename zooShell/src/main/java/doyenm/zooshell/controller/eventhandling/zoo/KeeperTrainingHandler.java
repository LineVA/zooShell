package doyenm.zooshell.controller.eventhandling.zoo;

import doyenm.zooshell.context.EvaluationContext;

/**
 *
 * @author doyenm
 */
public class KeeperTrainingHandler implements ZooEventsHandler{

    @Override
    public boolean canExecute(EvaluationContext t) {
       if(t.getKeepers().isEmpty()){
           return false;
       }
       int nbOfTurns = t.getZoo().getAge() / t.getZoo().getMonthsPerEvaluation();
       return nbOfTurns % 15 == 0;
    }

    @Override
    public ZooEvent execute(EvaluationContext t) {
        return new ZooEvent(ZooEventType.KEEPER_TRAINING);
    }
}
