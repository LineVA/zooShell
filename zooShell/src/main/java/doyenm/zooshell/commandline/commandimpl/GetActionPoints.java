package doyenm.zooshell.commandline.commandimpl;

import doyenm.zooshell.commandline.general.Command;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.utils.Constants;

import java.util.Arrays;

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
                && Arrays.asList(Constants.ACTION_POINTS)
                .stream()
                .anyMatch(cmd[0]::equalsIgnoreCase);
    }
}
