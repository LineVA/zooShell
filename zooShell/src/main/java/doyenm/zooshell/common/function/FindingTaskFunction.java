package doyenm.zooshell.common.function;

import doyenm.zooshell.model.TaskType;
import doyenm.zooshell.common.context.FindingTaskContext;

import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class FindingTaskFunction implements Function<FindingTaskContext, FindingTaskContext> {

    ReplaceSpacesWithUnderscores replaceSpacesWithUnderscores = new ReplaceSpacesWithUnderscores();

    @Override
    public FindingTaskContext apply(FindingTaskContext t) {
        FindingTaskContext context = t;
        try {
            int id = Integer.parseInt(context.getTask());
            for (TaskType task : TaskType.values()) {
                if (id == task.getId()) {
                    context.setConvertedTask(task);
                }
            }
        } catch (NumberFormatException ex) {
            String input = replaceSpacesWithUnderscores.replace(context.getTask());
            for (TaskType task : TaskType.values()) {
                if (input.equalsIgnoreCase(task.toString())) {
                    context.setConvertedTask(task);
                }
            }
        }
        return context;
    }

}
