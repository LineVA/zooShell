package doyenm.zooshell.paddock.facilities;

import doyenm.zooshell.LsContext;
import doyenm.zooshell.commandline.general.Command;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.commandline.utils.FormattingInList;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.utils.Constants;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author doyenm
 */
public class LsPaddockFacilities implements Command {

    @Override
    public ReturnExec execute(String[] cmd, Zoo zoo) {
        LsContext context = new LsContext(zoo);
        return Stream.of(context)
                .map(t ->
                        new ReturnExec(FormattingInList.formatList(context.getPaddockFacilities()), TypeReturn.SUCCESS)
                )
                .findFirst()
                .orElseGet(null);
    }

    @Override
    public boolean canExecute(String[] cmd) {
        return cmd.length == 1
                && Arrays.asList(Constants.FACILITIES)
                .stream()
                .anyMatch(cmd[0]::equalsIgnoreCase);
    }
}