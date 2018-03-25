package doyenm.zooshell.commandline.general.displayingevent;

import doyenm.zooshell.controller.eventhandling.Event;
import doyenm.zooshell.controller.eventhandling.EventCategory;
import doyenm.zooshell.controller.eventhandling.EventSubject;

/**
 *
 * @author doyenm
 */
public class DisplayingUnaryZooEvents implements DisplayingEvents {

    @Override
    public boolean canFormat(Event event) {
      return EventCategory.UNARY == event.getEventCategory() 
              && EventSubject.ZOO_EVENT == event.getEventSubject();
    }

    @Override
    public String format(Event event) {
        return null;
    }

}
