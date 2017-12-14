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
        context.getPaddock().setObsolescence(
                obsolescenceAddedDuringTheTurn 
                        + context.getPaddock().getObsolescence());
        return context;
    }
    
}
