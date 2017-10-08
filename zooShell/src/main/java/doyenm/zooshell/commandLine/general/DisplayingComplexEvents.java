package doyenm.zooshell.commandLine.general;

import doyenm.zooshell.controller.eventhandling.Event;
import doyenm.zooshell.controller.eventhandling.EventCategory;
import doyenm.zooshell.controller.eventhandling.EventType;
import java.text.MessageFormat;

/**
 *
 * @author doyenm
 */
public class DisplayingComplexEvents implements DisplayingEvents{

    @Override
    public boolean canFormat(Event event) {
        return EventCategory.BIRTH == event.getEventType().getCategory() ||
                EventType.DEATH_OF_PREDATION == event.getEventType();
    }

    @Override
    public String format(Event event) {
        return MessageFormat.format(event.getEventType().getMessage(), 
                event.getSubject().getName(),
                event.getActor().getName());
    }
}