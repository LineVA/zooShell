package doyenm.zooshell.controller.eventhandling.paddock;

import doyenm.zooshell.controller.eventhandling.Event;
import doyenm.zooshell.controller.eventhandling.EventCategory;
import doyenm.zooshell.controller.eventhandling.EventSubject;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.PaddockState;
import lombok.Getter;

/**
 *
 * @author doyenm
 */
@Getter
public class PaddockEvent implements Event{

    private final PaddockEventType eventType;
    private final Paddock subject;
    private PaddockState actor;

    public PaddockEvent(PaddockEventType eventType, Paddock subject) {
        if (EventCategory.UNARY == eventType.getCategory()) {
            this.eventType = eventType;
            this.subject = subject;
        } else {
            this.eventType = null;
            this.subject = null;
        }
    }

    public PaddockEvent(PaddockEventType eventType, Paddock subject, PaddockState actor) {
        if (EventCategory.BINARY == eventType.getCategory()) {
            this.eventType = eventType;
            this.subject = subject;
            this.actor = actor;
        } else {
            this.eventType = null;
            this.subject = null;
        }
    }

    @Override
    public EventCategory getEventCategory() {
        return eventType.getCategory();
    }
    
    

    @Override
    public EventSubject getEventSubject() {
        return EventSubject.ANIMAL_EVENT;
    }

}
