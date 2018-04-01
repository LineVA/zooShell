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
public class DisplayingBinaryAnimalEvents implements DisplayingEvents {

    @Override
    public boolean canFormat(Event event) {
        return EventSubject.ANIMAL_EVENT == event.getEventSubject()
                && EventCategory.BINARY == event.getEventCategory();
    }

    @Override
    public String format(Event event) {
        AnimalEvent animalEvent = (AnimalEvent) event;

        return MessageFormat.format(animalEvent.getEventType().getMessage(),
                animalEvent.getSubject().getName(),
                animalEvent.getActor().getName());
    }
}
