package doyenm.zooshell.controller.paddockcontroller.evaluation;

import doyenm.zooshell.context.PaddockEvaluationContext;
import doyenm.zooshell.model.PaddockState;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class UpdateUnusableState
        implements Function<PaddockEvaluationContext, PaddockEvaluationContext> {

    private final Function<Double, PaddockState> paddockStateFunction;

    @Override
    public PaddockEvaluationContext apply(PaddockEvaluationContext t) {
        PaddockState state = paddockStateFunction.apply(t.getPaddock().getObsolescence());
        if (PaddockState.UNSUABLE == state) {
            t.getPaddock().setTurnsOfUnusableState(1 + t.getPaddock().getTurnsOfUnusableState());
        } else {
            t.getPaddock().setTurnsOfUnusableState(0);
        }
        return t;
    }

}
