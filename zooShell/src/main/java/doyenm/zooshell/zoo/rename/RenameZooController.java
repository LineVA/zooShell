package doyenm.zooshell.zoo.rename;

import doyenm.zooshell.zoo.ZooContext;

import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class RenameZooController implements Function<ZooContext, ZooContext>{

    @Override
    public ZooContext apply(ZooContext t) {
        t.getZoo().setName(t.getSaveName());
        return t;
    }
}
