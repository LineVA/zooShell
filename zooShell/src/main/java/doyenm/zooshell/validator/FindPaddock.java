package doyenm.zooshell.validator;

import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.validator.context.FindingPaddockContext;
import doyenm.zooshell.validator.function.FindingPaddockByNameFunction;
import java.util.stream.Stream;

/**
 *
 * @author doyenm
 */
public class FindPaddock {

    FindingPaddockByNameFunction findingPaddockFunction = new FindingPaddockByNameFunction();

    public Paddock find(Zoo zoo, String padName) {
        FindingPaddockContext findingPaddockContext = new FindingPaddockContext(zoo.getPaddocks(), padName);
        return Stream.of(findingPaddockContext)
                .map(findingPaddockFunction)
                .findFirst()
                .get()
                .getPaddock();
    }
}
