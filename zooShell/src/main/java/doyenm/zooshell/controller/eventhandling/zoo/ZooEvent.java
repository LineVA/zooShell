package doyenm.zooshell.controller.eventhandling.zoo;

import doyenm.zooshell.controller.eventhandling.Event;
import doyenm.zooshell.controller.eventhandling.EventCategory;
import doyenm.zooshell.controller.eventhandling.EventSubject;
import doyenm.zooshell.model.AnimalKeeper;
import doyenm.zooshell.model.Family;
import lombok.Builder;

/**
 *
 * @author doyenm
 */
@Builder
public class ZooEvent implements Event{
    private final ZooEventType type;
    private final AnimalKeeper keeper;
    private final Family family;

    @Override
    public EventCategory getEventCategory() {
        return type.getCategory();
    }

    @Override
    public EventSubject getEventSubject() {
        return EventSubject.ZOO_EVENT;
    }
    
}
