package doyenm.zooshell.controller.zoocontroller;

import doyenm.zooshell.context.EvaluationContext;
import doyenm.zooshell.controller.eventhandling.zoo.KeeperTrainingHandler;
import doyenm.zooshell.controller.eventhandling.zoo.ZooEvent;
import doyenm.zooshell.controller.eventhandling.zoo.ZooEventsHandler;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *
 * @author doyenm
 */
public class ZooEvaluationController  implements Function<EvaluationContext, EvaluationContext> {
    
    private List<ZooEventsHandler> handlers = Arrays.asList(new KeeperTrainingHandler());

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
    
    private List<ZooEvent> generateZooEvents(EvaluationContext context){
        return handlers
                .stream()
                .map(handler -> handler.apply(context))
                .collect(Collectors.toList());
    }
    
}
