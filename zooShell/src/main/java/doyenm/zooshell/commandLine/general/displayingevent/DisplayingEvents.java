package doyenm.zooshell.commandLine.general.displayingevent;

import doyenm.zooshell.controller.eventhandling.Event;

/**
 *
 * @author doyenm
 */
public interface DisplayingEvents {

    boolean canFormat(Event event);

    String format(Event event);
}
