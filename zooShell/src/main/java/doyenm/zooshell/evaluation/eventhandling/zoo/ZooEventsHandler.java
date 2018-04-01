package doyenm.zooshell.evaluation.eventhandling.zoo;

import doyenm.zooshell.evaluation.EvaluationContext;

/**
 *
 * @author doyenm
 */
public interface ZooEventsHandler {

    public boolean canExecute(EvaluationContext t);

    public ZooEvent execute(EvaluationContext t);

}
