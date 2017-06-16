package doyenm.zooshell.controller.keepercontroller;

import doyenm.zooshell.context.KeeperCreationContext;
import doyenm.zooshell.model.AnimalKeeper;
import doyenm.zooshell.model.TaskType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class KeeperCreationController
        implements Function<KeeperCreationContext, KeeperCreationContext> {

    @Override
    public KeeperCreationContext apply(KeeperCreationContext t) {
        KeeperCreationContext context = t;
        AnimalKeeper keeper = AnimalKeeper.builder()
                .name(context.getKeeper())
                .occupations(new ArrayList<>())
                .tasksMap(generateTaskTypesMap())
                .build();
        context.getZoo().getKeepers().put(context.getKeeper(), keeper);
        return context;
    }
    
    private Map<TaskType, Double> generateTaskTypesMap(){
        Map<TaskType, Double> map = new HashMap<>();
        for(TaskType task : TaskType.values()){
            map.put(task, 0.0);
        }
        return map;
    }

}
