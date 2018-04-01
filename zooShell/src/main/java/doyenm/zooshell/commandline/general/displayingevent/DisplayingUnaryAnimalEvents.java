package doyenm.zooshell.commandline.general.displayingevent;

import doyenm.zooshell.evaluation.eventhandling.Event;
import doyenm.zooshell.evaluation.eventhandling.EventCategory;
import doyenm.zooshell.evaluation.eventhandling.EventSubject;
import doyenm.zooshell.evaluation.eventhandling.animal.AnimalEvent;

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
