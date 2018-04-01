package doyenm.zooshell.evaluation.paddock;

import doyenm.zooshell.evaluation.PaddockEvaluationContext;

import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class PaddockAgeEvaluationController
        implements Function<PaddockEvaluationContext, PaddockEvaluationContext> {

    @Override
    public PaddockEvaluationContext apply(PaddockEvaluationContext t) {
        PaddockEvaluationContext context = t;
        context.getPaddock().setAge(context.getPaddock().getAge() + t.getZoo().getMonthsPerEvaluation());
        return context;
    }

}
