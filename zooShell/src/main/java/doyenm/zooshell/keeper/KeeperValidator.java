package doyenm.zooshell.keeper;

import doyenm.zooshell.common.FindKeeper;

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
