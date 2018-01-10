package doyenm.zooshell.controller.handymancontroller;

import doyenm.zooshell.context.HandymanRenameContext;
import doyenm.zooshell.model.Handyman;
import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class RenamingController     
        implements Function<HandymanRenameContext, HandymanRenameContext> {

    @Override
    public HandymanRenameContext apply(HandymanRenameContext t) {
        Handyman handyman = t.getHandyman();
        handyman.setName(t.getNewName());
        t.getZoo().getHandymen().remove(t.getCurrentName());
        t.getZoo().getHandymen().put(handyman.getName().toUpperCase(), handyman);
        return t;
    }

}
