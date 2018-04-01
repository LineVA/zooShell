package doyenm.zooshell.evaluation.paddock;

import doyenm.zooshell.evaluation.PaddockEvaluationContext;
import doyenm.zooshell.evaluation.eventhandling.paddock.PaddockEvent;
import doyenm.zooshell.evaluation.eventhandling.paddock.PaddockEventType;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.PaddockState;
import lombok.RequiredArgsConstructor;

import java.util.function.Function;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class ObsolescenceEvaluationController
        implements Function<PaddockEvaluationContext, PaddockEvaluationContext> {

    private final Function addedObsolescenceFunction;
    private final Function erasedObsolescenceFunction;
    private final Function obsolescenceToStateFunction;

    @Override
    public PaddockEvaluationContext apply(PaddockEvaluationContext t) {
        PaddockEvaluationContext context = t;
        LightZooDto dto = LightZooDto.makeLightZooDto(context);
        Double addedObsolescenceDuringTheTurn = (Double) addedObsolescenceFunction.apply(dto);
        Double erasedObsolescenceDuringTheTurn = (Double) erasedObsolescenceFunction.apply(dto);
        double init = context.getPaddock().getObsolescence();
        double sum = addedObsolescenceDuringTheTurn - erasedObsolescenceDuringTheTurn
                + init;
        sum = sum < 0.0 ? 0.0 : sum;
        sum = sum > 1.0 ? 1.0 : sum;
        context.getPaddock().setObsolescence(sum);
        if (changeState(init, sum)) {
            context.getPaddockEvents().add(generateEvent(sum, context.getPaddock()));
        }
        return context;
    }

    private boolean changeState(double init, double sum) {
        return obsolescenceToStateFunction.apply(init)
                != obsolescenceToStateFunction.apply(sum);
    }

    private PaddockEvent generateEvent(double sum, Paddock pad) {
        return new PaddockEvent(PaddockEventType.CHANGE_STATE, pad, (PaddockState) obsolescenceToStateFunction.apply(sum));
    }

}
