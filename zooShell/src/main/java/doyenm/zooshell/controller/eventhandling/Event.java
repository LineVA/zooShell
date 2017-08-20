package doyenm.zooshell.controller.eventhandling;

import doyenm.zooshell.model.Animal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
public class Event {
    private final EventType eventType;
    private final Animal subject;
    private Animal mother;
}
