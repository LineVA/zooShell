package doyenm.zooshell.controller.paddockcontroller;

import doyenm.zooshell.context.EvaluationContext;
import doyenm.zooshell.context.PaddockEvaluationContext;
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

    @Override
    public EvaluationContext apply(EvaluationContext t) {
        EvaluationContext context = t;
        context.setEvaluatedPaddocksList(context.getPaddocks().values()
                .stream()
                .map((Paddock t1) -> new PaddockEvaluationContext(context.getZoo(), t1))
                // Age
                .map(paddockAgeEvaluationController)
                .map((PaddockEvaluationContext t1) -> t1.getPaddock())
                .collect(Collectors.toList())
        );
        return context;
    }

}
