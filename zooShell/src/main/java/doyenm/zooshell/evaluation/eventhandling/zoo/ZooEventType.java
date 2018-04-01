package doyenm.zooshell.evaluation.eventhandling.zoo;

import doyenm.zooshell.evaluation.eventhandling.EventCategory;
import lombok.Getter;

/**
 *
 * @author doyenm
 */
public enum ZooEventType {

    KEEPER_TRAINING(EventCategory.NONE, "A keeper can have a training");

    @Getter
    private EventCategory category;

    @Getter
    private final String message;

    private ZooEventType(EventCategory category, String message) {
        this.category = category;
        this.message = message;
    }
}
