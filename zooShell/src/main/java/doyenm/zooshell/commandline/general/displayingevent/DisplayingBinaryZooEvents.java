package doyenm.zooshell.commandline.general.displayingevent;

import doyenm.zooshell.controller.eventhandling.Event;
import doyenm.zooshell.controller.eventhandling.animal.AnimalEvent;
import doyenm.zooshell.controller.eventhandling.EventCategory;
import doyenm.zooshell.controller.eventhandling.EventSubject;
import doyenm.zooshell.controller.eventhandling.zoo.ZooEvent;
import java.text.MessageFormat;

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
        ZooEvent zooEvent = (ZooEvent) event;
//        return MessageFormat.format(zooEvent.getEventType().getMessage(),
//                zooEvent.getSubject().getName(),
//                zooEvent.getActor().getName());
        return null;
    }
}
