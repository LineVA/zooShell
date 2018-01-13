package doyenm.zooshell.commandLine.commandImpl;

import doyenm.zooshell.commandLine.general.Command;
import doyenm.zooshell.commandLine.general.ReturnExec;
import doyenm.zooshell.commandLine.general.TypeReturn;
import doyenm.zooshell.commandLine.utils.FormattingInList;
import doyenm.zooshell.context.LsContext;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.utils.Constants;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 *
 * @author doyenm
 */
public class LsSpecie implements Command {

    @Override
    public ReturnExec execute(String[] cmd, Zoo zoo) {
        LsContext context = new LsContext(zoo);
        return Stream.of(context)
                .map(t -> {
                    FormattingInList formatting = new FormattingInList();
                    return new ReturnExec(formatting.formatList(t.getSpecieNames()), TypeReturn.SUCCESS);
                })
                .findFirst()
                .get();
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 1) {
            return Arrays.asList(Constants.SPECIES).contains(cmd[0]);
        }
        return false;
    }
}
