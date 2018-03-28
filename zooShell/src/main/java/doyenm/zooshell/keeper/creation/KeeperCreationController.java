package doyenm.zooshell.keeper.creation;

import doyenm.zooshell.keeper.creation.KeeperCreationContext;
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
    
    private static final int INIT_AGE = 0;

    @Override
    public KeeperCreationContext apply(KeeperCreationContext t) {
        KeeperCreationContext context = t;
        AnimalKeeper keeper = AnimalKeeper.builder()
                .name(context.getKeeper())
                .age(INIT_AGE)
                .occupations(new ArrayList<>())
                .taskEvaluations(new EnumMap<>(TaskType.class))
                .familyEvaluations(new EnumMap<>(Family.class))
                .training(null)
                .build();
        context.getZoo().getKeepers().put(context.getKeeper().toUpperCase(), keeper);
        return context;
    }

}
