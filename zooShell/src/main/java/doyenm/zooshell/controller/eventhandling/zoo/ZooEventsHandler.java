package doyenm.zooshell.controller.eventhandling.zoo;

import doyenm.zooshell.context.EvaluationContext;
import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public abstract class ZooEventsHandler implements Function<EvaluationContext, ZooEvent> {

    public abstract ZooEvent apply(EvaluationContext t);
}
