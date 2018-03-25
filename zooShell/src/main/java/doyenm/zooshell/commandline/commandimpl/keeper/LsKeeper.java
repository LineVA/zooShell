package doyenm.zooshell.commandline.commandimpl.keeper;

import doyenm.zooshell.commandline.general.Command;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.commandline.utils.FormattingInList;
import doyenm.zooshell.context.LsContext;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.utils.Constants;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author doyenm
 */
public class LsKeeper implements Command {

    @Override
    public ReturnExec execute(String[] cmd, Zoo zoo) {
        LsContext context = new LsContext(zoo);
        return Stream.of(context)
                .map((LsContext t) -> {
                    FormattingInList formatting = new FormattingInList();
                    return new ReturnExec(formatting.formatList(context.getKeeperNames()), TypeReturn.SUCCESS);
                })
                .findFirst()
                .get();
    }

    @Override
    public boolean canExecute(String[] cmd) {
        return cmd.length == 1
                && Arrays.asList(Constants.KEEPERS_OR_ANIMALKEEPERS).contains(cmd[0]);
    }

}
