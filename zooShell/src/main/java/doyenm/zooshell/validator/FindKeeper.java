package doyenm.zooshell.validator;

import doyenm.zooshell.model.AnimalKeeper;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.validator.context.FindingKeeperContext;
import doyenm.zooshell.validator.function.FindingKeeperFunction;
import java.util.stream.Stream;

/**
 *
 * @author doyenm
 */
public class FindKeeper {

    FindingKeeperFunction findingKeeperFunction = new FindingKeeperFunction();

    public AnimalKeeper find(Zoo zoo, String keeperName) {
        FindingKeeperContext findingKeeperContext = new FindingKeeperContext(zoo.getKeepers(), keeperName);
        return Stream.of(findingKeeperContext)
                .map(findingKeeperFunction)
                .findFirst()
                .get()
                .getConvertedKeeper();
    }
}
