package doyenm.zooshell.paddock.facilities;

import doyenm.zooshell.commandline.general.Command;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.commandline.utils.FormattingInList;
import doyenm.zooshell.LsContext;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.utils.Constants;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 *
 * @author doyenm
 */
public class LsPaddockArrangements implements Command {

    @Override
    public ReturnExec execute(String[] cmd, Zoo zoo) {
        LsContext context = new LsContext(zoo);
        return Stream.of(context)
                .map(new Function<LsContext, ReturnExec>() {
                    @Override
                    public ReturnExec apply(LsContext t) {
                        FormattingInList formatting = new FormattingInList();
                        return new ReturnExec(formatting.formatList(context.getPaddockArrangements()), TypeReturn.SUCCESS);
                    }

                })
                .findFirst()
                .get();
    }

    @Override
    public boolean canExecute(String[] cmd) {
        return cmd.length == 1
                 && Arrays.asList(Constants.PAD_OR_PADDOCK_ARRANGEMENT)
                .stream()
                .anyMatch(cmd[0]::equalsIgnoreCase);
    }
}