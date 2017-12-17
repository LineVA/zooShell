package doyenm.zooshell.controller.handymancontroller;

import doyenm.zooshell.context.HandymanCreationContext;
import doyenm.zooshell.model.Handyman;
import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class CreationController
        implements Function<HandymanCreationContext, HandymanCreationContext>{

    @Override
    public HandymanCreationContext apply(HandymanCreationContext t) {
        Handyman handyman = Handyman.builder()
                .name(t.getName())
                .age(0)
                .build();
        t.getZoo().getHandymen().put(t.getName().toUpperCase(), handyman);
        return t;
    }
    
}
