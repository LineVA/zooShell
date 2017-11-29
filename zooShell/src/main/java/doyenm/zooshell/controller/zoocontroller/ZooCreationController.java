package doyenm.zooshell.controller.zoocontroller;

import java.util.function.Function;
import doyenm.zooshell.context.ZooCreationContext;

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
