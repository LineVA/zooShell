package doyenm.zooshell.evaluation.eventhandling.zoo;

import doyenm.zooshell.evaluation.eventhandling.Event;
import doyenm.zooshell.evaluation.eventhandling.EventCategory;
import doyenm.zooshell.evaluation.eventhandling.EventSubject;
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
