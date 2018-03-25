package doyenm.zooshell.commandline.general;

import doyenm.zooshell.commandline.commandimpl.Evaluate;
import doyenm.zooshell.commandline.commandimpl.GetActionPoints;
import doyenm.zooshell.model.Zoo;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class CommandManager {

    private final List<ActionPointCommand> commands;
    private final ActionPointsHandler actionPointsHandler;
    private final GetActionPoints getActionPoints;
    private final Evaluate evaluate;

    public static final String FIRST_LINE = "Welcome";

    private Zoo zoo;

    public ReturnExec run(String cmd) {
        String[] cmdArray = SplitDoubleQuotes.split(cmd);
        ReturnExec result;
        if (getActionPoints.canExecute(cmdArray)) {
            return executeGetActionPoints(cmdArray);
        }
        if (evaluate.canExecute(cmdArray)) {
            return executeEvaluate(cmdArray);
        }
        for (ActionPointCommand actionPointCommand : commands) {
            if (actionPointCommand.getCommand().canExecute(cmdArray)) {
                if (actionPointsHandler.hasEnoughPoints(actionPointCommand.getActionPointsNumber())) {
                    result = save(actionPointCommand.getCommand().execute(cmdArray, zoo));
                    if (result.getTypeReturn() == TypeReturn.SUCCESS) {
                        actionPointsHandler.update(actionPointCommand.getActionPointsNumber());
                        if (actionPointCommand.getActionPointsNumber() != 0) {
                            result.concat(actionPointsHandler.updateMessage());
                        }
                    }
                    return result;
                } else {
                    return new ReturnExec("NOT_ENOUGH_ACTION_POINTS", TypeReturn.ERROR, null);
                }
            }
        }
        return new ReturnExec("UNKNOWN CMD", TypeReturn.ERROR, null);
    }


    private ReturnExec save(ReturnExec returnExec) {
        if (TypeReturn.SUCCESS == returnExec.getTypeReturn() && returnExec.getZoo() != null) {
            this.zoo = returnExec.getZoo();
        }
        return returnExec;
    }

    private ReturnExec executeGetActionPoints(String[] cmdArray) {
        ReturnExec aPResult = getActionPoints.execute(cmdArray, null);
        aPResult.concat(actionPointsHandler.updateMessage());
        return aPResult;
    }

    private ReturnExec executeEvaluate(String[] cmdArray) {
        ReturnExec result = evaluate.execute(cmdArray, zoo);
        actionPointsHandler.recompute(zoo);
        return result;
    }
}
