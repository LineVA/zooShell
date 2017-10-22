package doyenm.zooshell.controller.eventhandling;

/**
 *
 * @author doyenm
 */
public interface Event {

    EventCategory getEventCategory();
    
    EventSubject getEventSubject();
    
}
