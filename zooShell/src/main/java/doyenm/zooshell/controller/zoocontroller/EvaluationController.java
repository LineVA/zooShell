package doyenm.zooshell.controller.zoocontroller;

import doyenm.zooshell.context.EvaluationContext;
import doyenm.zooshell.controller.animalcontroller.AnimalEvaluationController;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 *
 * @author doyenm
 */
public class EvaluationController implements Function<EvaluationContext, EvaluationContext> {

    AnimalEvaluationController animalEvaluationController = new AnimalEvaluationController();
    ZooEvaluationController zooEvaluationController = new ZooEvaluationController();

    @Override
    public EvaluationContext apply(EvaluationContext t) {
        EvaluationContext context = t;
        return Stream.of(context)
                .map(zooEvaluationController)
                //                .map(paddocksEvaluationController)
                .map(animalEvaluationController)
//                .map(keeperEvaluationController)
                .map((EvaluationContext t1) -> {
                    t1.setTotalEvaluation(t1.getZooEvaluation() + t1.getPaddocksEvaluation() + t1.getAnimalsEvaluation());
                    return t1;
                })
                .findFirst()
                .get();
    }

}
