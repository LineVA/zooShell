package doyenm.zooshell.controller.eventhandling;

import lombok.Getter;

/**
 *
 * @author doyenm
 */
public enum EventType {

    DEATH_OF_AGE("The animal {0} is dead of old age"),
    DEATH_OF_DROWN("The animal {0} is dead of drown"),
    DEATH_OF_HUNGER("The animal {0} is dead of starvation"),
    DIYING_OF_UNGER("The animal {0} is dying of starvation"),
    DIYING_OF_DROWN("The animal {0} is diying of drowning");
    
    @Getter
    private final String message;

    private EventType(String message) {
        this.message = message;
    }
}
