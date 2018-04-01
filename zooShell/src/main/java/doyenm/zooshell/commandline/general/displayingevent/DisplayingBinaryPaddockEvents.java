package doyenm.zooshell.commandline.general.displayingevent;

import doyenm.zooshell.evaluation.eventhandling.Event;
import doyenm.zooshell.evaluation.eventhandling.EventCategory;
import doyenm.zooshell.evaluation.eventhandling.EventSubject;
import doyenm.zooshell.evaluation.eventhandling.paddock.PaddockEvent;

import java.text.MessageFormat;

/**
 *
 * @author doyenm
 */
public class DisplayingBinaryPaddockEvents implements DisplayingEvents {

    @Override
    public boolean canFormat(Event event) {
        return EventSubject.PADDOCK_EVENT == event.getEventSubject()
                && EventCategory.BINARY == event.getEventCategory();
    }

    @Override
    public String format(Event event) {
        PaddockEvent paddockEvent = (PaddockEvent) event;

        return MessageFormat.format(paddockEvent.getEventType().getMessage(),
                paddockEvent.getSubject().getName(),
                paddockEvent.getActor().toString());
    }
}
