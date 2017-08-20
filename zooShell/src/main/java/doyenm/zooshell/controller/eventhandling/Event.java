package doyenm.zooshell.controller.eventhandling;

import doyenm.zooshell.model.Animal;
import java.util.Arrays;
import java.util.List;
import lombok.Getter;

/**
 *
 * @author doyenm
 */
@Getter
public class Event {

    private final EventType eventType;
    private final Animal subject;
    private Animal mother;

    private final List<EventCategory> justSubject = Arrays.asList(EventCategory.DEATH);
    private final List<EventCategory> subjectAndMother = Arrays.asList(EventCategory.BIRTH);

    public Event(EventType eventType, Animal subject) {
        if (justSubject.contains(eventType.getCategory())) {
            this.eventType = eventType;
            this.subject = subject;
        } else {
            this.eventType = null;
            this.subject = null;
        }
    }
    
      public Event(EventType eventType, Animal subject, Animal mother) {
        if (subjectAndMother.contains(eventType.getCategory())) {
            this.eventType = eventType;
            this.subject = subject;
        } else {
            this.eventType = null;
            this.subject = null;
        }
    }

}
