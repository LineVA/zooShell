package doyenm.zooshell.commandline.general.displayingevent;

import doyenm.zooshell.controller.eventhandling.Event;
import doyenm.zooshell.controller.eventhandling.EventCategory;
import doyenm.zooshell.controller.eventhandling.EventSubject;
import doyenm.zooshell.controller.eventhandling.zoo.ZooEvent;

/**
 *
 * @author doyenm
 */
public class DisplayingBinaryZooEvents implements DisplayingEvents {

    @Override
    public boolean canFormat(Event event) {
        return EventCategory.BINARY == event.getEventCategory() &&
                EventSubject.ZOO_EVENT == event.getEventSubject();
    }

    @Override
    public String format(Event event) {
        return null;
    }
}
