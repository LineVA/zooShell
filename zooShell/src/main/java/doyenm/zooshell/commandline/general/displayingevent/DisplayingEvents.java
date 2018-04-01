package doyenm.zooshell.commandline.general.displayingevent;

import doyenm.zooshell.evaluation.eventhandling.Event;

/**
 *
 * @author doyenm
 */
public interface DisplayingEvents {

    boolean canFormat(Event event);

    String format(Event event);
}
