package doyenm.zooshell.validator;

import doyenm.zooshell.context.KeeperAddTrainingContext;
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
public class KeeperAddTrainingValidator implements Predicate<KeeperAddTrainingContext> {

    private final FindKeeper findKeeper;
    private final FindingFamilyFunction findingFamilyFunction;

    @Override
    public boolean test(KeeperAddTrainingContext t) {
        t.setKeeper(findKeeper.find(t.getZoo(), t.getKeeperName()));
        FindingFamilyContext findingContext = new FindingFamilyContext(t.getFamilyName());
        t.setFamily(Stream.of(findingContext)
                .map(findingFamilyFunction)
                .findFirst()
                .get()
                .getConvertedFamily());
        return t.getKeeper() != null && t.getFamily() != null
                && !t.getZoo().getAvailableKeeperTrainings().isEmpty()
                && t.getKeeper().getTraining() == null;
    }

}
