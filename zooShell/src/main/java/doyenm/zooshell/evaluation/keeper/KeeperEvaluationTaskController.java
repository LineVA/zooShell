package doyenm.zooshell.evaluation.keeper;

import doyenm.zooshell.evaluation.KeeperEvaluationContext;
import doyenm.zooshell.model.TaskType;
import doyenm.zooshell.model.TimedOccupation;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *
 * @author doyenm
 */
public class KeeperEvaluationTaskController
        implements Function<KeeperEvaluationContext, KeeperEvaluationContext> {

    @Override
    public KeeperEvaluationContext apply(KeeperEvaluationContext t) {
        KeeperEvaluationContext context = t;
        if (context.getKeeper().getTraining() != null) {
            return context;
        } else {
            return computeTaskProgression(context);
        }
    }

    private KeeperEvaluationContext computeTaskProgression(KeeperEvaluationContext context) {
        Map<TaskType, Double> map = generateTimedTaskMap(context.getOccupations());
        for (Map.Entry<TaskType, Double> entry : map.entrySet()) {
            if (context.getTaskEvaluationMap().containsKey(entry.getKey())) {
                double currentValue = context.getTaskEvaluationMap().get(entry.getKey());
                context.getKeeper().getTaskEvaluations().replace(entry.getKey(), entry.getValue() + currentValue);
            } else {
                context.getKeeper().getTaskEvaluations().put(entry.getKey(), entry.getValue());
            }
        }
        return context;
    }

    private Map<TaskType, Double> generateTimedTaskMap(List<TimedOccupation> occupations) {
        return occupations
                .stream()
                .collect(
                        Collectors.groupingBy(TimedOccupation::getTaskType,
                                Collectors.summingDouble(TimedOccupation::getTime)));
    }

}
