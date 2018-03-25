package doyenm.zooshell.commandline.general.displayingevent;

import doyenm.zooshell.controller.eventhandling.Event;
import doyenm.zooshell.controller.eventhandling.EventCategory;
import doyenm.zooshell.controller.eventhandling.EventSubject;
import doyenm.zooshell.controller.eventhandling.zoo.ZooEvent;

/**
 *
 * @author doyenm
 */
public class DisplayingNoneZooEvents implements DisplayingEvents {

    @Override
    public boolean canFormat(Event event) {
        return EventCategory.NONE == event.getEventCategory()
                && EventSubject.ZOO_EVENT == event.getEventSubject();
    }

    @Override
    public String format(Event event) {
        ZooEvent zooEvent = (ZooEvent) event;
        return zooEvent.getEventType().getMessage();
    }

}
