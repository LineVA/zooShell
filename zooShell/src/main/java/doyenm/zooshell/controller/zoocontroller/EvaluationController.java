package doyenm.zooshell.controller.zoocontroller;

import doyenm.zooshell.context.EvaluationContext;
import doyenm.zooshell.controller.animalcontroller.AnimalEvaluationController;
import doyenm.zooshell.controller.keepercontroller.KeeperEvaluationController;
import doyenm.zooshell.controller.paddockcontroller.PaddockEvaluationController;
import java.util.function.Function;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class EvaluationController implements Function<EvaluationContext, EvaluationContext> {

    private final AnimalEvaluationController animalEvaluationController;
    private final KeeperEvaluationController keeperEvaluationController;
    private final PaddockEvaluationController paddockEvaluationController;
    private final ZooEvaluationController zooEvaluationController;

    @Override
    public EvaluationContext apply(EvaluationContext t) {
        EvaluationContext context = t;
        context = Stream.of(context)
                .map(zooEvaluationController)
                .map(paddockEvaluationController)
                .map(animalEvaluationController)
                .map(keeperEvaluationController)
                .map((EvaluationContext t1) -> {
//                    t1.setTotalEvaluation(t1.getZooEvaluation() + t1.getPaddocksEvaluation() + t1.getAnimalsEvaluation());
                    return t1;
                })
                .findFirst()
                .get();
        context.updateAnimals();
        return context;
    }

}
