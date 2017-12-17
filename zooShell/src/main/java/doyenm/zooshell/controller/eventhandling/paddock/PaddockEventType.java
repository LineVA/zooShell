package doyenm.zooshell.controller.eventhandling.paddock;

import doyenm.zooshell.controller.eventhandling.EventCategory;
import lombok.Getter;

/**
 *
 * @author doyenm
 */
public enum PaddockEventType {

   CHANGE_STATE(EventCategory.BINARY, "The state of the paddock {0} has changed : its state is now {1}.");

    @Getter
    private EventCategory category;
    
    @Getter
    private final String message;

    private PaddockEventType(EventCategory category, String message) {
        this.category = category;
        this.message = message;
    }
}
