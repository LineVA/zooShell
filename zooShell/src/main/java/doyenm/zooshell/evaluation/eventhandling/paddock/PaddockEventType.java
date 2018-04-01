package doyenm.zooshell.evaluation.eventhandling.paddock;

import doyenm.zooshell.evaluation.eventhandling.EventCategory;
import lombok.Getter;

/**
 *
 * @author doyenm
 */
public enum PaddockEventType {

   CHANGE_STATE(EventCategory.BINARY, "The state of the paddock {0} has changed : its state is now {1}."),
   STILL_UNUSABLE(EventCategory.BINARY, "The paddock {0} is still UNUSABLE");
   

    @Getter
    private EventCategory category;
    
    @Getter
    private final String message;

    private PaddockEventType(EventCategory category, String message) {
        this.category = category;
        this.message = message;
    }
}
