package doyenm.zooshell.controller.zoocontroller;

import doyenm.zooshell.context.EvaluationContext;
import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class ZooEvaluationController  implements Function<EvaluationContext, EvaluationContext> {

    @Override
    public EvaluationContext apply(EvaluationContext t) {
        EvaluationContext context = t;
        context.getZoo().setAge(context.getZoo().getAge() + context.getZoo().getMonthsPerEvaluation());
        context.setZooEvaluation(0);
        return context;
    }

}
