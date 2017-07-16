package doyenm.zooshell.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author doyenm
 */
@Getter
@Setter
@Builder
@ToString
public class TimedOccupation {

    private final Paddock paddock;
    private final TaskType taskType;
    private final double time;
}
