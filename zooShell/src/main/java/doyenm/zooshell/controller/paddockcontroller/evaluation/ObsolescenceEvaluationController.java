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

    private final Function function;
    
    @Override
    public PaddockEvaluationContext apply(PaddockEvaluationContext t) {
        PaddockEvaluationContext context = t;
        int animals = (int) context.getZoo().getAnimals().values()
                .stream()
                .filter(animal -> animal.getPaddock().equals(context.getPaddock()))
                .count();
        LightZooDto dto = new LightZooDto(
                context.getPaddock(), 
                context.getZoo().getMonthsPerEvaluation(),
                animals);
        Double obsolescenceAddedDuringTheTurn = (Double)function.apply(dto);
        context.getPaddock().setObsolescence(obsolescenceAddedDuringTheTurn);
        return context;
    }
    
}
