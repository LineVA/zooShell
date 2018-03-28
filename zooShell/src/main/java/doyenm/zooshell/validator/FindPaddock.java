package doyenm.zooshell.validator;

import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.common.context.FindingPaddockContext;
import doyenm.zooshell.common.function.FindingPaddockByNameFunction;

import java.util.stream.Stream;

/**
 *
 * @author doyenm
 */
public class FindPaddock {

    FindingPaddockByNameFunction findingPaddockFunction = new FindingPaddockByNameFunction();

    public Paddock find(Zoo zoo, String padName) {
        FindingPaddockContext findingPaddockContext = new FindingPaddockContext(zoo.getPaddocks(), padName.toUpperCase());
        return Stream.of(findingPaddockContext)
                .map(findingPaddockFunction)
                .findFirst()
                .get()
                .getPaddock();
    }
}
