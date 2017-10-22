package doyenm.zooshell.model;

import lombok.Builder;
import lombok.Getter;

/**
 *
 * @author doyenm
 */
@Builder
@Getter
public class Training {

    private int remainingTurnsNumber;
    private final Family subject;
    private final double bonus;
}
