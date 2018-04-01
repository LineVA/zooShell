package doyenm.zooshell.evaluation.keeper;

import doyenm.zooshell.evaluation.KeeperEvaluationContext;

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
        context.getKeeper().setAge(context.getKeeperAge() + context.getMonthsPerTurn());
        return context;
    }
}
