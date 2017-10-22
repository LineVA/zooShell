package doyenm.zooshell.controller.eventhandling.animal;

import doyenm.zooshell.controller.eventhandling.EventCategory;
import lombok.Getter;

/**
 *
 * @author doyenm
 */
public enum AnimalEventType {

    DEATH_OF_AGE(EventCategory.UNARY, "The animal {0} is dead of old age"),
    DEATH_OF_DROWN(EventCategory.UNARY, "The animal {0} is dead of drown"),
    DEATH_OF_HUNGER(EventCategory.UNARY, "The animal {0} is dead of starvation"),
    DEATH_OF_PREDATION(EventCategory.BINARY, "The animal {0} is dead, eaten by {1}"),
    DIYING_OF_HUNGER(EventCategory.UNARY, "The animal {0} is dying of starvation"),
    DIYING_OF_DROWN(EventCategory.UNARY, "The animal {0} is diying of drowning"),
    BIRTH(EventCategory.UNARY, "The animal {0} is born ; its mother is {1}"),
    NEW_PREGNANCY(EventCategory.UNARY, "The animal {0} is now pregnant"),
    PREGNANCY_PURSUIT(EventCategory.UNARY, "The animal {0} is still pregnant");

    @Getter
    private EventCategory category;

    @Getter
    private final String message;

    private AnimalEventType(EventCategory category, String message) {
        this.category = category;
        this.message = message;
    }
}
