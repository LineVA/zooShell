package doyenm.zooshell.zoo;

import doyenm.zooshell.evaluation.EvaluationContext;
import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class PenaltiesEvaluationController
        implements Function<EvaluationContext, EvaluationContext> {

    @Override
    public EvaluationContext apply(EvaluationContext t) {
        t.getZoo().getPenalties()
                .stream()
                .forEach( penalty -> {
                   t.getGradeObj().setPenaltiesGrade(penalty.getValue() + t.getGradeObj().getPenaltiesGrade());
                   penalty.setRemainingTurns(penalty.getRemainingTurns() - 1);
                });
        t.getZoo().getPenalties().removeIf(penalty -> penalty.getRemainingTurns() == 0);
        return t;
    }

}
