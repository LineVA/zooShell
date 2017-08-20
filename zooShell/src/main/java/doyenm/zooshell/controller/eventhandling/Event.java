package doyenm.zooshell.controller.eventhandling;

import doyenm.zooshell.model.Animal;
import java.util.Arrays;
import java.util.List;
import lombok.Getter;

/**
 *
 * @author doyenm
 */
public class Event {

    @Getter
    private final EventType eventType;
    @Getter
    private final Animal subject;
    @Getter
    private Animal mother;

    private final List<EventCategory> justSubject = Arrays.asList(EventCategory.DEATH, EventCategory.PREGNANCY);
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
            this.mother = mother;
        } else {
            this.eventType = null;
            this.subject = null;
        }
    }

}
