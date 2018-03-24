package doyenm.zooshell.controller.zoocontroller;

import doyenm.zooshell.context.EvaluationContext;
import doyenm.zooshell.controller.eventhandling.zoo.ZooEvent;
import doyenm.zooshell.controller.eventhandling.zoo.ZooEventsHandler;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class ZooEvaluationController implements Function<EvaluationContext, EvaluationContext> {

    private final List<ZooEventsHandler> handlers;

    @Override
    public EvaluationContext apply(EvaluationContext t) {
        EvaluationContext context = t;
        context.getZoo().setAge(context.getZoo().getAge() + context.getZoo().getMonthsPerEvaluation());
        context.getGradeObj().setZooGrade(0.0);

        // Generate and save the events related to the zoo itself
        List<ZooEvent> zooEvents = generateZooEvents(context);
        context.setZooEvents(zooEvents);
        context.getZoo().getZooEvents().addAll(zooEvents);

        return context;
    }

    private List<ZooEvent> generateZooEvents(EvaluationContext context) {
        return handlers
                .stream()
                .map(handler -> {
                    if (handler.canExecute(context)) {
                        return handler.execute(context);
                    } 
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

}
