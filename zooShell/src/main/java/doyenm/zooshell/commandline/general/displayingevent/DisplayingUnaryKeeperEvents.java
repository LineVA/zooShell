package doyenm.zooshell.commandline.general.displayingevent;

import doyenm.zooshell.controller.eventhandling.Event;
import doyenm.zooshell.controller.eventhandling.EventCategory;
import doyenm.zooshell.controller.eventhandling.EventSubject;
import doyenm.zooshell.controller.eventhandling.keeper.KeeperEvent;

import java.text.MessageFormat;

/**
 *
 * @author doyenm
 */
public class DisplayingUnaryKeeperEvents implements DisplayingEvents {

    @Override
    public boolean canFormat(Event event) {
      return EventCategory.UNARY == event.getEventCategory()
              && EventSubject.KEEPER_EVENT == event.getEventSubject();
    }

    @Override
    public String format(Event event) {
        KeeperEvent keeperEvent = (KeeperEvent) event;
        return MessageFormat.format(keeperEvent.getEventType().getMessage(), keeperEvent.getSubject().getName());
    }

}
