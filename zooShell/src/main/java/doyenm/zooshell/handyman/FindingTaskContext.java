package doyenm.zooshell.handyman;

import doyenm.zooshell.model.TaskType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 *
 * @author doyenm
 */
@Setter
@Getter
@RequiredArgsConstructor
public class FindingTaskContext {

    private final String task;
    private TaskType convertedTask;
}
