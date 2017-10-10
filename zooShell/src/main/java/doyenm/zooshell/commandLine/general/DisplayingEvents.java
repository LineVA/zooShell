package doyenm.zooshell.commandLine.general;

import doyenm.zooshell.controller.eventhandling.AnimalEvent;

/**
 *
 * @author doyenm
 */
public interface DisplayingEvents {

    boolean canFormat(AnimalEvent event);

    String format(AnimalEvent event);
}
