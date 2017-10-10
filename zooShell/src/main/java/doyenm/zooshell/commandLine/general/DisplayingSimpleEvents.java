package doyenm.zooshell.commandLine.general;

import doyenm.zooshell.controller.eventhandling.AnimalEvent;
import doyenm.zooshell.controller.eventhandling.EventCategory;
import doyenm.zooshell.controller.eventhandling.AnimalEventType;
import java.text.MessageFormat;

/**
 *
 * @author doyenm
 */
public class DisplayingSimpleEvents implements DisplayingEvents {

    @Override
    public boolean canFormat(AnimalEvent event) {
        return (EventCategory.DEATH == event.getEventType().getCategory()
                && AnimalEventType.DEATH_OF_PREDATION != event.getEventType())
                || EventCategory.PREGNANCY == event.getEventType().getCategory();
    }

    @Override
    public String format(AnimalEvent event) {
        return MessageFormat.format(event.getEventType().getMessage(), event.getSubject().getName());
    }
}
