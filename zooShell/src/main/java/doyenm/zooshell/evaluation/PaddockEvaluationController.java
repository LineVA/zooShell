package doyenm.zooshell.evaluation;

import doyenm.zooshell.evaluation.EvaluationContext;
import doyenm.zooshell.evaluation.PaddockEvaluationContext;
import doyenm.zooshell.evaluation.paddock.ObsolescenceEvaluationController;
import doyenm.zooshell.evaluation.paddock.PaddockAgeEvaluationController;
import doyenm.zooshell.evaluation.paddock.PaddockEvacuationController;
import doyenm.zooshell.evaluation.paddock.UpdateUnusableState;
import doyenm.zooshell.model.Paddock;
import lombok.RequiredArgsConstructor;

import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class PaddockEvaluationController implements Function<EvaluationContext, EvaluationContext> {

    private final PaddockAgeEvaluationController paddockAgeEvaluationController;
    private final ObsolescenceEvaluationController obsolescenceEvaluationController;
    private final UpdateUnusableState updateUnusableState;
    private final PaddockEvacuationController paddockEvacuationController;

    @Override
    public EvaluationContext apply(EvaluationContext t) {
        EvaluationContext context = t;
        context.setEvaluatedPaddocksList(context.getPaddocks().values()
                .stream()
                .map((Paddock t1) -> new PaddockEvaluationContext(context.getZoo(), t1))
                // Age
                .map(paddockAgeEvaluationController)
                .map(obsolescenceEvaluationController)
                .map(updateUnusableState)
                .map(paddockEvacuationController)
                .map((PaddockEvaluationContext t1) -> {
                    context.getPaddockEvents().addAll(t1.getPaddockEvents());
                    return t1;
                })
                .map(PaddockEvaluationContext::getPaddock)
                .collect(Collectors.toList())
        );
        return context;
    }

}
