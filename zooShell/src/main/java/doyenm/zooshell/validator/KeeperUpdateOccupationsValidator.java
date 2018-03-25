package doyenm.zooshell.validator;

import doyenm.zooshell.context.KeeperUpdateOccupationsContext;
import doyenm.zooshell.model.TaskType;
import doyenm.zooshell.model.TimedOccupation;
import doyenm.zooshell.validator.context.FindingTaskContext;
import doyenm.zooshell.validator.function.FindingTaskFunction;
import doyenm.zooshell.validator.predicates.DoubleValuesPredicates;
import lombok.RequiredArgsConstructor;

import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class KeeperUpdateOccupationsValidator
        implements Predicate<KeeperUpdateOccupationsContext> {

    private final FindKeeper findKeeper;
    private final FindPaddock findPaddock;
    private final FindingTaskFunction findingTaskFunction;
    private final DoubleValuesPredicates doubleValuesPredicates;
    
    private final int maxTurnsInUnusableState;

    @Override
    public boolean test(KeeperUpdateOccupationsContext t) {
        t.convert();
        t.setConvertedKeeper(findKeeper.find(t.getZoo(), t.getKeeper()));
        t.setConvertedPaddock(findPaddock.find(t.getZoo(), t.getPaddock()));
        FindingTaskContext findingContext = new FindingTaskContext(t.getTask());
        t.setConvertedTask(Stream.of(findingContext)
                .map(findingTaskFunction)
                .findFirst()
                .orElseGet(null)
                .getConvertedTask());
        if (t.getConvertedKeeper() != null && t.getConvertedPaddock() != null && t.getConvertedTask() != null) {
            return testTime(t) && t.getConvertedPaddock().getEntry() != null && t.getConvertedTask() != TaskType.UNKNOWN
                    && t.getConvertedPaddock().getTurnsOfUnusableState() < maxTurnsInUnusableState;
        }
        return false;
    }

    private boolean testTime(KeeperUpdateOccupationsContext t) {
        if(!this.doubleValuesPredicates.mustBeGreaterOrEqualsThan(t.getConvertedTime(), 0.0)){
            return false;
        }
        double sum = 0.0;
        for(TimedOccupation occ : t.getConvertedKeeper().getOccupations()){
            if(!occ.getPaddock().equals(t.getConvertedPaddock()) || !occ.getTaskType().equals(t.getConvertedTask())){
                sum += t.getConvertedTime();
            }
        }
        sum += t.getConvertedTime();
        return this.doubleValuesPredicates.mustBeLowerOrEqualsThan(sum, 100.0);
    }

}
