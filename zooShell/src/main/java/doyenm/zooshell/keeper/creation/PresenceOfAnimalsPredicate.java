package doyenm.zooshell.keeper.creation;

import java.util.function.Predicate;

public class PresenceOfAnimalsPredicate implements Predicate<KeeperCreationContext> {

    public boolean test(KeeperCreationContext context) {
        return context.getAnimals().size() > 0;
    }
}
