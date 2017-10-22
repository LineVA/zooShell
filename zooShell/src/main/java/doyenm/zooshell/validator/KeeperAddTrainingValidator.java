package doyenm.zooshell.validator;

import doyenm.zooshell.context.KeeperTrainingContext;
import doyenm.zooshell.controller.eventhandling.zoo.ZooEventType;
import doyenm.zooshell.model.Family;
import doyenm.zooshell.validator.context.FindingFamilyContext;
import doyenm.zooshell.validator.function.FindingFamilyFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class KeeperAddTrainingValidator implements Predicate<KeeperTrainingContext> {

    private final FindKeeper findKeeper;
    private final FindingFamilyFunction findingFamily;
    
    @Override
    public boolean test(KeeperTrainingContext t) {
        KeeperTrainingContext context = t;
        context.getZoo().getZooEvents()
                .stream()
                .filter(event -> event.getEventType() == ZooEventType.KEEPER_TRAINING)
                .filter(event -> event.getKeeper() != null)
                .findFirst();
        context.setConvertedKeeper(findKeeper.find(t.getZoo(), t.getName()));
        FindingFamilyContext findingContext = new FindingFamilyContext(t.getFamilyName());
        t.setConvertedFamily(Stream.of(findingContext)
                .map(findingFamily)
                .findFirst()
                .get()
                .getConvertedFamily());
        if (t.getConvertedKeeper() != null & t.getConvertedFamily()!= null) {
            return t.getConvertedFamily()!= Family.UNKNOWN;
        }
        return false;
    }

}
