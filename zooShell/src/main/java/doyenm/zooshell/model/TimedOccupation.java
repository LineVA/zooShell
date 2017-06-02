package doyenm.zooshell.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author doyenm
 */
@Getter
@Setter
@Builder
public class TimedOccupation {

    private final Paddock paddock;
    private final TaskType taskType;
    private final double time;

    @Override
    public String toString() {
        return "TimedOccupation{" + "paddock=" + paddock + ", taskType=" + taskType + ", time=" + time + '}';
    }
    
    
}
