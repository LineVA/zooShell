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
    private Animal actor;

    private final List<EventCategory> justSubject = Arrays.asList(EventCategory.DEATH, EventCategory.PREGNANCY);
    private final List<EventCategory> subjectAndActor = Arrays.asList(EventCategory.BIRTH, EventCategory.DEATH);

    public Event(EventType eventType, Animal subject) {
        if (justSubject.contains(eventType.getCategory())) {
            this.eventType = eventType;
            this.subject = subject;
        } else {
            this.eventType = null;
            this.subject = null;
        }
    }

    public Event(EventType eventType, Animal subject, Animal actor) {
        if (subjectAndActor.contains(eventType.getCategory())) {
            this.eventType = eventType;
            this.subject = subject;
            this.actor = actor;
        } else {
            this.eventType = null;
            this.subject = null;
        }
    }

}
