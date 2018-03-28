package doyenm.zooshell.zoo.creation;

import doyenm.zooshell.zoo.creation.ZooCreationContext;

import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class ZooCreationController implements Function<ZooCreationContext, ZooCreationContext>{

    @Override
    public ZooCreationContext apply(ZooCreationContext t) {
       t.build();
       return t;
    }

}
