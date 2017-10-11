package doyenm.zooshell.commandLine.general;

import doyenm.zooshell.controller.eventhandling.AnimalEvent;
import doyenm.zooshell.controller.eventhandling.EventCategory;
import java.text.MessageFormat;

/**
 *
 * @author doyenm
 */
public class DisplayingSimpleEvents implements DisplayingEvents {

    @Override
    public boolean canFormat(AnimalEvent event) {
        return EventCategory.UNARY == event.getEventType().getCategory();
    }

    @Override
    public String format(AnimalEvent event) {
        return MessageFormat.format(event.getEventType().getMessage(), event.getSubject().getName());
    }
}
