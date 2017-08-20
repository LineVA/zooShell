package doyenm.zooshell.commandLine.general;

import doyenm.zooshell.controller.eventhandling.Event;

/**
 *
 * @author doyenm
 */
public interface DisplayingEvents {

    boolean canFormat(Event event);

    String format(Event event);
}
