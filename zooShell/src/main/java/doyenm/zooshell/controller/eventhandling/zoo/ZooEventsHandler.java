package doyenm.zooshell.controller.eventhandling.zoo;

import doyenm.zooshell.context.EvaluationContext;

/**
 *
 * @author doyenm
 */
public interface ZooEventsHandler {

    public boolean canExecute(EvaluationContext t);

    public ZooEvent execute(EvaluationContext t);

}
