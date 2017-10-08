package doyenm.zooshell.commandLine.general;

import doyenm.zooshell.controller.eventhandling.Event;
import doyenm.zooshell.controller.eventhandling.EventCategory;
import doyenm.zooshell.controller.eventhandling.EventType;
import java.text.MessageFormat;

/**
 *
 * @author doyenm
 */
public class DisplayingSimpleEvents implements DisplayingEvents {

    @Override
    public boolean canFormat(Event event) {
        return (EventCategory.DEATH == event.getEventType().getCategory()
                && EventType.DEATH_OF_PREDATION != event.getEventType())
                || EventCategory.PREGNANCY == event.getEventType().getCategory();
    }

    @Override
    public String format(Event event) {
        return MessageFormat.format(event.getEventType().getMessage(), event.getSubject().getName());
    }
}
