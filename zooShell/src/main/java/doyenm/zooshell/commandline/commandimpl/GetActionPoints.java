package doyenm.zooshell.commandline.commandimpl;

import doyenm.zooshell.commandline.general.Command;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.utils.Constants;

/**
 * @author doyenm
 */
public class GetActionPoints implements Command {

    @Override
    public ReturnExec execute(String[] cmd, Zoo zoo) {
        return new ReturnExec("", TypeReturn.SUCCESS);
    }

    @Override
    public boolean canExecute(String[] cmd) {
        return cmd.length == 1
                && Constants.ACTION_POINTS.equals(cmd[0]);
    }
}
