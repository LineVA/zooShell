package doyenm.zooshell.controller.eventhandling;

import doyenm.zooshell.model.Animal;
import lombok.Getter;

/**
 *
 * @author doyenm
 */
public class AnimalEvent {

    @Getter
    private final AnimalEventType eventType;
    @Getter
    private final Animal subject;
    @Getter
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

}
