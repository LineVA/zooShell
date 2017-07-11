package doyenm.zooshell.commandLine.general;

import doyenm.zooshell.model.Zoo;
import java.util.List;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class CommandManager {

    private final List<ActionPointCommand> commands;
    private final ActionPointsHandler actionPointsHandler;

    private Zoo zoo;

    public ReturnExec run(String cmd) {
        String[] cmdArray = SplitDoubleQuotes.split(cmd);
        ReturnExec result;
        for (ActionPointCommand actionPointCommand : commands) {
            if (actionPointCommand.getCommand().canExecute(cmdArray)) {
                if (actionPointsHandler.hasEnoughPoints(actionPointCommand.getActionPointsNumber())) {
                    result = save(actionPointCommand.getCommand().execute(cmdArray, zoo));
                    if(result.getTypeReturn() == TypeReturn.SUCCESS){
                        actionPointsHandler.update(actionPointCommand.getActionPointsNumber());
                    }
                    return result;
                } else {
                    return new ReturnExec("NOT_ENOUGH_ACTION_POINTS", TypeReturn.ERROR, null);
                }
            }
        }
        return new ReturnExec("UNKNOWN CMD", TypeReturn.ERROR, null);
    }

    public String getFirstLine() {
        return "Welcome";
    }

    private ReturnExec save(ReturnExec returnExec) {
        if (TypeReturn.SUCCESS == returnExec.getTypeReturn() && returnExec.getZoo() != null) {
            this.zoo = returnExec.getZoo();
        }
        return returnExec;
    }
}
