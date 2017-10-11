package doyenm.zooshell.commandLine.general;

import doyenm.zooshell.controller.eventhandling.AnimalEvent;
import doyenm.zooshell.controller.eventhandling.EventCategory;
import java.text.MessageFormat;

/**
 *
 * @author doyenm
 */
public class DisplayingComplexEvents implements DisplayingEvents{

    @Override
    public boolean canFormat(AnimalEvent event) {
        return EventCategory.BINARY == event.getEventType().getCategory();
    }

    @Override
    public String format(AnimalEvent event) {
        return MessageFormat.format(event.getEventType().getMessage(), 
                event.getSubject().getName(),
                event.getActor().getName());
    }
}