package doyenm.zooshell.controller.keepercontroller;

import doyenm.zooshell.context.KeeperContext;
import doyenm.zooshell.model.AnimalKeeper;
import doyenm.zooshell.model.Family;
import doyenm.zooshell.model.TaskType;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *
 * @author doyenm
 */
public class KeeperDetailsController implements Function<KeeperContext, KeeperContext> {

    @Override
    public KeeperContext apply(KeeperContext t) {
        KeeperContext context = t;
        AnimalKeeper keeper = context.getConvertedKeeper();
        context.addCouple("Name", keeper.getName());
        context.addCouple("Occupations", keeper.getOccupations().toString());
        context.addCouple("Average task evaluation", computeAverageTaskEvaluation(
                context.getConvertedKeeper().getTaskEvaluations()));
        context.addCouple("Average family evaluation", computeAverageFamilyEvaluation(
                context.getConvertedKeeper().getFamilyEvaluations()));
        return context;
    }

    private double computeAverageTaskEvaluation(Map<TaskType, Double> map) {
        return map
                .values()
                .parallelStream()
                .collect(Collectors.summingDouble(d -> d));
    }
    
      private double computeAverageFamilyEvaluation(Map<Family, Double> map) {
        return map
                .values()
                .parallelStream()
                .collect(Collectors.summingDouble(d -> d));
    }

}
