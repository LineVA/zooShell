package doyenm.zooshell.controller.keepercontroller;

import doyenm.zooshell.context.EvaluationContext;
import doyenm.zooshell.context.KeeperEvaluationContext;
import doyenm.zooshell.model.AnimalKeeper;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *
 * @author doyenm
 */
public class KeeperEvaluationController implements Function<EvaluationContext, EvaluationContext> {

    KeeperEvaluationTaskController taskController = new KeeperEvaluationTaskController();
    KeeperEvaluationFamilyController familyController = new KeeperEvaluationFamilyController();

    @Override
    public EvaluationContext apply(EvaluationContext t) {
        EvaluationContext context = t;
        context.setEvaluatedKeepersList(context.getKeepers().values()
                .stream()
                .map((AnimalKeeper t1) -> new KeeperEvaluationContext(context.getZoo(), t1))
                .map(taskController)
                .map(familyController)
                .map((KeeperEvaluationContext t1) -> t1.getKeeper())
                .collect(Collectors.toList())
        );
        return context;
    }

}