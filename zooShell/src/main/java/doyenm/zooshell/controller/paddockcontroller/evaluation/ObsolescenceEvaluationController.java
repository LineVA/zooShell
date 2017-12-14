package doyenm.zooshell.controller.paddockcontroller.evaluation;

import doyenm.zooshell.context.PaddockEvaluationContext;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class ObsolescenceEvaluationController 
          implements Function<PaddockEvaluationContext, PaddockEvaluationContext> {

    private final Function osolescenceFunction;
    
    @Override
    public PaddockEvaluationContext apply(PaddockEvaluationContext t) {
        PaddockEvaluationContext context = t;
        LightZooDto dto = LightZooDto.makeLightZooDto(context);
        Double obsolescenceAddedDuringTheTurn = (Double)osolescenceFunction.apply(dto);
        double sum = obsolescenceAddedDuringTheTurn 
                        + context.getPaddock().getObsolescence();
        context.getPaddock().setObsolescence(sum > 1.0 ? 1.0 : sum);
        return context;
    }
    
}
