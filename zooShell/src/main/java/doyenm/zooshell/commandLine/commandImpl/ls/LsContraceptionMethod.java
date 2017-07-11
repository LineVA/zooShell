package doyenm.zooshell.commandLine.commandImpl.ls;

import doyenm.zooshell.commandLine.general.Command;
import doyenm.zooshell.commandLine.general.ReturnExec;
import doyenm.zooshell.commandLine.general.TypeReturn;
import doyenm.zooshell.commandLine.utils.FormattingInList;
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
public class LsContraceptionMethod implements Command{

    @Override
    public ReturnExec execute(String[] cmd, Zoo zoo) {
        LsContext context = new LsContext(zoo);
        return Stream.of(context)
                .map(new Function<LsContext, ReturnExec>() {
                    @Override
                    public ReturnExec apply(LsContext t) {
                        FormattingInList formatting = new FormattingInList();
                        return new ReturnExec(formatting.formatList(context.getContraceptionMethods()), 
                                TypeReturn.SUCCESS);
                    }

                })
                .findFirst()
                .get();
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 2) {
            if (Arrays.asList(Constants.LS).contains(cmd[0])) {
                if (Arrays.asList(Constants.CONTRACEPTIONMETHOD).contains(cmd[1])) {
                    return true;
                }
            }
        }
        return false;
    }
}