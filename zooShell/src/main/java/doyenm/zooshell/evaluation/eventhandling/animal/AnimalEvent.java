package doyenm.zooshell.evaluation.eventhandling.animal;

import doyenm.zooshell.evaluation.eventhandling.Event;
import doyenm.zooshell.evaluation.eventhandling.EventCategory;
import doyenm.zooshell.evaluation.eventhandling.EventSubject;
import doyenm.zooshell.model.Animal;
import lombok.Getter;

/**
 *
 * @author doyenm
 */
@Getter
public class AnimalEvent implements Event{

    private final AnimalEventType eventType;
    private final Animal subject;
    private Animal actor;

    public AnimalEvent(AnimalEventType eventType, Animal subject) {
        if (EventCategory.UNARY == eventType.getCategory()) {
            this.eventType = eventType;
            this.subject = subject;
        } else {
            this.eventType = null;
            this.subject = null;
        }
    }

    public AnimalEvent(AnimalEventType eventType, Animal subject, Animal actor) {
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
