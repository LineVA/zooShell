package doyenm.zooshell.commandline.commandimpl.ls;

import doyenm.zooshell.commandline.general.Command;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.commandline.utils.FormattingInList;
import doyenm.zooshell.context.LsContext;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.utils.Constants;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 *
 * @author doyenm
 */
public class LsKeeperTask implements Command{

    @Override
    public ReturnExec execute(String[] cmd, Zoo zoo) {
        LsContext context = new LsContext(zoo);
        return Stream.of(context)
                .map(new Function<LsContext, ReturnExec>() {
                    @Override
                    public ReturnExec apply(LsContext t) {
                        FormattingInList formatting = new FormattingInList();
                        return new ReturnExec(formatting.formatList(context.getTasks()), TypeReturn.SUCCESS);
                    }

                })
                .findFirst()
                .get();
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 1) {
                if (Arrays.asList(Constants.TASKS).contains(cmd[0])) {
                    return true;
            }
        }
        return false;
    }
}