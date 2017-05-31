package doyenm.zooshell.validator;

import doyenm.zooshell.context.KeeperContext;
import doyenm.zooshell.validator.context.FindingKeeperContext;
import doyenm.zooshell.validator.function.FindingKeeperFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 *
 * @author doyenm
 */
public class KeeperDetailsValidator implements Predicate<KeeperContext> {

    FindingKeeperFunction findingKeeperFunction = new FindingKeeperFunction();

    @Override
    public boolean test(KeeperContext t) {
        FindingKeeperContext findingKeeperContext = new FindingKeeperContext(t.getZoo().getKeepers(), t.getKeeper());

        t.setConvertedKeeper(Stream.of(findingKeeperContext)
                .map(findingKeeperFunction)
                .findFirst()
                .get()
                .getConvertedKeeper());
        return t.getConvertedKeeper()!= null;
    }

}
