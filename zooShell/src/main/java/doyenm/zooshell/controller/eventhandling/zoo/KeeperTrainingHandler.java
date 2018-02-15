package doyenm.zooshell.controller.eventhandling.zoo;

import doyenm.zooshell.context.EvaluationContext;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class KeeperTrainingHandler implements ZooEventsHandler{

    private final int numberOfTurnsBetwwenTwoTrainings;
    
    @Override
    public boolean canExecute(EvaluationContext t) {
       if(t.getKeepers().isEmpty()){
           return false;
       }
       int nbOfTurns = t.getZoo().getAge() / t.getZoo().getMonthsPerEvaluation();
       return nbOfTurns % numberOfTurnsBetwwenTwoTrainings == 0;
    }

    @Override
    public ZooEvent execute(EvaluationContext t) {
        return new ZooEvent(ZooEventType.KEEPER_TRAINING);
    }
}
