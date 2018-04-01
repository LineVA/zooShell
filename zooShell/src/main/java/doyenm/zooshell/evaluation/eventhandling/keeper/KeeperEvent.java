package doyenm.zooshell.evaluation.eventhandling.keeper;

import doyenm.zooshell.evaluation.eventhandling.Event;
import doyenm.zooshell.evaluation.eventhandling.EventCategory;
import doyenm.zooshell.evaluation.eventhandling.EventSubject;
import doyenm.zooshell.model.AnimalKeeper;
import lombok.Getter;

/**
 *
 * @author doyenm
 */
@Getter
public class KeeperEvent implements Event {

    private final KeeperEventType eventType;
    private final AnimalKeeper subject;
    private AnimalKeeper actor;

    public KeeperEvent(KeeperEventType eventType, AnimalKeeper subject) {
        if (EventCategory.UNARY == eventType.getCategory()) {
            this.eventType = eventType;
            this.subject = subject;
        } else {
            this.eventType = null;
            this.subject = null;
        }
    }

    @Override
    public EventCategory getEventCategory() {
        return eventType.getCategory();
    }

    @Override
    public EventSubject getEventSubject() {
        return EventSubject.KEEPER_EVENT;
    }
}
