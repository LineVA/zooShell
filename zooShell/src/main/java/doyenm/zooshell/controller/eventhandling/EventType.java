package doyenm.zooshell.controller.eventhandling;

import lombok.Getter;

/**
 *
 * @author doyenm
 */
public enum EventType {

    DEATH_BY_AGE("The animal {0} is dead of old age"),
    DEATH_BY_DROWNING("The animal {0} is dead by drown"),
    DEAT_BY_HUNGER("The animal {0} is dead of starvation");
    
    @Getter
    private final String message;

    private EventType(String message) {
        this.message = message;
    }
}
