package doyenm.zooshell.controller.zoocontroller;

import doyenm.zooshell.context.ZooCreationContext;

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
