package doyenm.zooshell.validator;

import doyenm.zooshell.context.KeeperContext;
import java.util.function.Predicate;

/**
 *
 * @author doyenm
 */
public class KeeperValidator implements Predicate<KeeperContext> {

    FindKeeper findKeeper = new FindKeeper();

    @Override
    public boolean test(KeeperContext t) {
        t.setConvertedKeeper(findKeeper.find(t.getZoo(), t.getKeeper()));
        return t.getConvertedKeeper()!= null;
    }

}
