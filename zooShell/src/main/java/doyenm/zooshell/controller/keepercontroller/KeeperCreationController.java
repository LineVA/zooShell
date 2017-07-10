package doyenm.zooshell.controller.keepercontroller;

import doyenm.zooshell.context.KeeperCreationContext;
import doyenm.zooshell.model.AnimalKeeper;
import doyenm.zooshell.model.Family;
import doyenm.zooshell.model.TaskType;
import java.util.ArrayList;
import java.util.EnumMap;
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
                .taskEvaluations(new EnumMap<>(TaskType.class))
                .familyEvaluations(new EnumMap<>(Family.class))
                .build();
        context.getZoo().getKeepers().put(context.getKeeper(), keeper);
        return context;
    }

}
