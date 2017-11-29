package doyenm.zooshell.commandLine.commandImpl;

import doyenm.zooshell.commandLine.general.Command;
import doyenm.zooshell.commandLine.general.ReturnExec;
import doyenm.zooshell.commandLine.general.TypeReturn;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.utils.Constants;

/**
 *
 * @author doyenm
 */
public class GetActionPoints implements Command{

    @Override
    public ReturnExec execute(String[] cmd, Zoo zoo) {
        return new ReturnExec("", TypeReturn.SUCCESS);
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 1) {
            if (Constants.ACTION_POINTS.equals(cmd[0])) {
                return true;
            }
        }
        return false;
    }
}
