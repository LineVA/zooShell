package doyenm.zooshell.commandLine.general.displayingevent;

import doyenm.zooshell.controller.eventhandling.Event;
import doyenm.zooshell.controller.eventhandling.EventCategory;
import doyenm.zooshell.controller.eventhandling.EventSubject;
import doyenm.zooshell.controller.eventhandling.animal.AnimalEvent;
import java.text.MessageFormat;

/**
 *
 * @author doyenm
 */
public class DisplayingUnaryAnimalEvents implements DisplayingEvents {

    @Override
    public boolean canFormat(Event event) {
      return EventCategory.UNARY == event.getEventCategory()
              && EventSubject.ANIMAL_EVENT == event.getEventSubject();
    }

    @Override
    public String format(Event event) {
        AnimalEvent animalEvent = (AnimalEvent) event;
        return MessageFormat.format(animalEvent.getEventType().getMessage(), animalEvent.getSubject().getName());
    }

}
