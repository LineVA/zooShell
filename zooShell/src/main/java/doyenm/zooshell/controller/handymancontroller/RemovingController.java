package doyenm.zooshell.controller.handymancontroller;

import doyenm.zooshell.context.HandymanContext;

import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class RemovingController
        implements Function<HandymanContext, HandymanContext>{

    @Override
    public HandymanContext apply(HandymanContext t) {
        t.getZoo().getHandymen().remove(t.getHandymanName().toUpperCase());
        return t;
    }
    
}
