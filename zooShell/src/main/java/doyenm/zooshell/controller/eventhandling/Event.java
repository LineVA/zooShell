package doyenm.zooshell.controller.eventhandling;

import doyenm.zooshell.model.Animal;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
@Getter
public class Event {
    private final EventType eventType;
    private final Animal subject;
}
