package doyenm.zooshell.controller.eventhandling;

import lombok.Getter;

/**
 *
 * @author doyenm
 */
public enum EventType {

    DEATH_OF_AGE("The animal {0} is dead of old age"),
    DEATH_OF_DROWN("The animal {0} is dead of drown"),
    DEAT_OF_HUNGER("The animal {0} is dead of starvation");
    
    @Getter
    private final String message;

    private EventType(String message) {
        this.message = message;
    }
}
