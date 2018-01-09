package doyenm.zooshell.controller.paddockcontroller.evaluation;

import doyenm.zooshell.context.PaddockEvaluationContext;
import doyenm.zooshell.controller.eventhandling.paddock.PaddockEvent;
import doyenm.zooshell.controller.eventhandling.paddock.PaddockEventType;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.PaddockState;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;

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
        context.getPaddock().setObsolescence(sum > 1.0 ? 1.0 : sum);
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
