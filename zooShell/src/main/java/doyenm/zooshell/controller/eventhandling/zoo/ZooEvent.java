package doyenm.zooshell.controller.eventhandling.zoo;

import doyenm.zooshell.controller.eventhandling.Event;
import doyenm.zooshell.controller.eventhandling.EventCategory;
import doyenm.zooshell.controller.eventhandling.EventSubject;
import lombok.Builder;
import lombok.Getter;

/**
 *
 * @author doyenm
 */
@Builder
@Getter
public class ZooEvent implements Event{
    private final ZooEventType eventType;

    @Override
    public EventCategory getEventCategory() {
        return eventType.getCategory();
    }

    @Override
    public EventSubject getEventSubject() {
        return EventSubject.ZOO_EVENT;
    }
    
}
