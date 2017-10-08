package doyenm.zooshell.controller.eventhandling;

import lombok.Getter;

/**
 *
 * @author doyenm
 */
public enum EventType {

    DEATH_OF_AGE(EventCategory.DEATH, "The animal {0} is dead of old age"),
    DEATH_OF_DROWN(EventCategory.DEATH, "The animal {0} is dead of drown"),
    DEATH_OF_HUNGER(EventCategory.DEATH, "The animal {0} is dead of starvation"),
    DEATH_OF_PREDATION(EventCategory.DEATH, "The animal {0} is dead, eaten by {1}"),
    DIYING_OF_UNGER(EventCategory.DEATH, "The animal {0} is dying of starvation"),
    DIYING_OF_DROWN(EventCategory.DEATH, "The animal {0} is diying of drowning"),
    BIRTH(EventCategory.BIRTH, "The animal {0} is born ; its mother is {1}"),
    NEW_PREGNANCY(EventCategory.PREGNANCY, "The animal {0} is now pregnant"),
    PREGNANCY_PURSUIT(EventCategory.PREGNANCY, "The animal {0} is still pregnant");

    @Getter
    private EventCategory category;

    @Getter
    private final String message;

    private EventType(EventCategory category, String message) {
        this.category = category;
        this.message = message;
    }
}
