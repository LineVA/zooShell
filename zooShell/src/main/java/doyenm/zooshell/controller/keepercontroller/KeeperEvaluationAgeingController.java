package doyenm.zooshell.controller.keepercontroller;

import doyenm.zooshell.context.KeeperEvaluationContext;
import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class KeeperEvaluationAgeingController
        implements Function<KeeperEvaluationContext, KeeperEvaluationContext> {

    @Override
    public KeeperEvaluationContext apply(KeeperEvaluationContext t) {
        KeeperEvaluationContext context = t;
        context.getKeeper().setAge(context.getKeeper().getAge() + context.getZoo().getMonthsPerEvaluation());
        return context;
    }
}
