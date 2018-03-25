package doyenm.zooshell.controller.paddockcontroller.evaluation;

import doyenm.zooshell.context.PaddockEvaluationContext;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.Penalty;
import doyenm.zooshell.model.PenaltyType;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class PaddockEvacuationController
        implements Function<PaddockEvaluationContext, PaddockEvaluationContext> {

    private final int maxTurnsWhenUnusable;
    private final int penaltyDuration;
    private final double penaltyValue;
    
    @Override
    public PaddockEvaluationContext apply(PaddockEvaluationContext t) {
        if (t.getPaddock().getTurnsOfUnusableState() == maxTurnsWhenUnusable) {
            // Delete animals
            handleAnimals(t);
            // Delete occupation s keepers
            handleKeepers(t);
            // Add penalty
            t.getZoo().getPenalties().add(Penalty.builder()
                    .remainingTurns(penaltyDuration)
                    .type(PenaltyType.UNUSABLE_PADDOCK)
                    .value(penaltyValue)
                    .build());
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
