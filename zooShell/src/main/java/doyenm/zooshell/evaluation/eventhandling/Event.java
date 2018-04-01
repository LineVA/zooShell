package doyenm.zooshell.evaluation.eventhandling;

/**
 *
 * @author doyenm
 */
public interface Event {

    EventCategory getEventCategory();
    
    EventSubject getEventSubject();
    
}
