package doyenm.zooshell.controller.paddockcontroller;

import doyenm.zooshell.controller.paddockcontroller.evaluation.PaddockAgeEvaluationController;
import doyenm.zooshell.context.EvaluationContext;
import doyenm.zooshell.context.PaddockEvaluationContext;
import doyenm.zooshell.controller.paddockcontroller.evaluation.ObsolescenceEvaluationController;
import doyenm.zooshell.controller.paddockcontroller.evaluation.PaddockEvacuationController;
import doyenm.zooshell.controller.paddockcontroller.evaluation.UpdateUnusableState;
import doyenm.zooshell.model.Paddock;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

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
                .map((PaddockEvaluationContext t1) -> t1.getPaddock())
                .collect(Collectors.toList())
        );
        return context;
    }

}
