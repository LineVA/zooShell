package doyenm.zooshell.controller.handymancontroller.evaluation;

import doyenm.zooshell.context.HandymanEvaluationContext;

import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class HandymanAgeEvaluationController
        implements Function<HandymanEvaluationContext, HandymanEvaluationContext> {

    @Override
    public HandymanEvaluationContext apply(HandymanEvaluationContext t) {
        t.getHandyman().setAge(t.getHandyman().getAge() + t.getZoo().getMonthsPerEvaluation());
        return t;
    }

}
