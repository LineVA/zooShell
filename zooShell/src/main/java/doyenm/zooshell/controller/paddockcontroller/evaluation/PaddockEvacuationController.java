package doyenm.zooshell.controller.paddockcontroller.evaluation;

import doyenm.zooshell.context.PaddockEvaluationContext;
import doyenm.zooshell.model.Paddock;
import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class PaddockEvacuationController
        implements Function<PaddockEvaluationContext, PaddockEvaluationContext> {

    @Override
    public PaddockEvaluationContext apply(PaddockEvaluationContext t) {
        if (t.getPaddock().getTurnsOfUnusableState() >= 3) {
            Paddock pad = t.getPaddock();
            // Delete animals 
            t = handleAnimals(t);
            // Delete occupation s keepers
            t = handleKeepers(t);
        }
        return t;
    }

    private PaddockEvaluationContext handleAnimals(PaddockEvaluationContext t) {
        Paddock pad = t.getPaddock();
        t.getAnimals().entrySet().removeIf(animal -> animal.getValue().getPaddock().equals(pad));
        return t;
    }

    private PaddockEvaluationContext handleKeepers(PaddockEvaluationContext t) {
        Paddock pad = t.getPaddock();
        t.getKeepers().values()
                .stream()
                .forEach(keeper
                        -> keeper.getOccupations().removeIf(occ -> pad.equals(occ.getPaddock()))
                );
        return t;
    }
}
