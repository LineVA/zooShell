package doyenm.zooshell.commandLine.commandImpl.paddock;

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
public class LsPaddock implements Command {
    
    @Override
    public ReturnExec execute(String[] cmd, Zoo zoo) {
        LsContext context = new LsContext(zoo);
        return Stream.of(context)
                .map(   (LsContext t) -> {
                    FormattingInList formatting = new FormattingInList();
                    return new ReturnExec(formatting.formatList(context.getPaddockNames()), TypeReturn.SUCCESS);
        })
                .findFirst()
                .get();
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 2) {
            if (Arrays.asList(Constants.LS).contains(cmd[0])) {
                if (Arrays.asList(Constants.PAD_OR_PADDOCK).contains(cmd[1])) {
                    return true;
                }
            }
        }
        return false;
    }

}
