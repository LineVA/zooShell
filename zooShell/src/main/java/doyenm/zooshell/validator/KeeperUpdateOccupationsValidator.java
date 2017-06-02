package doyenm.zooshell.validator;

import doyenm.zooshell.context.KeeperUpdateOccupationsContext;
import doyenm.zooshell.model.TimedOccupation;
import doyenm.zooshell.validator.context.FindingTaskContext;
import doyenm.zooshell.validator.function.FindingTaskFunction;
import doyenm.zooshell.validator.predicates.DoubleValuesPredicates;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.stream.Stream;

/**
 *
 * @author doyenm
 */
public class KeeperUpdateOccupationsValidator
        implements Predicate<KeeperUpdateOccupationsContext> {

    FindKeeper findKeeper = new FindKeeper();
    FindPaddock findPaddock = new FindPaddock();
    FindingTaskFunction findingTaskFunction = new FindingTaskFunction();
    DoubleValuesPredicates doubleValuesPredicates = new DoubleValuesPredicates();

    @Override
    public boolean test(KeeperUpdateOccupationsContext t) {
        t.convert();
        t.setConvertedKeeper(findKeeper.find(t.getZoo(), t.getKeeper()));
        t.setConvertedPaddock(findPaddock.find(t.getZoo(), t.getPaddock()));
        FindingTaskContext findingContext = new FindingTaskContext(t.getTask());
        t.setConvertedTask(Stream.of(findingContext)
                .map(findingTaskFunction)
                .findFirst()
                .get()
                .getConvertedTask());
        if (t.getConvertedKeeper() != null & t.getConvertedPaddock() != null & t.getConvertedTask() != null) {
            return testTime(t) & t.getConvertedPaddock().getEntry() != null;
        }
        return false;
    }

    private boolean testTime(KeeperUpdateOccupationsContext t) {
        if(!this.doubleValuesPredicates.mustBeGreaterOrEqualsThan(t.getConvertedTime(), 0.0)){
            return false;
        }
        double sum = 0.0;
        sum = t.getConvertedKeeper().getOccupations().stream().map(
                (d) -> t.getConvertedTime()).reduce(sum, (accumulator, d) -> accumulator + d);
        sum += t.getConvertedTime();
        return this.doubleValuesPredicates.mustBeLowerOrEqualsThan(sum, 100.0);
    }

}
