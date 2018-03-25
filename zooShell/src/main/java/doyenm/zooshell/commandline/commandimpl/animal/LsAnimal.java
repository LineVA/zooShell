package doyenm.zooshell.commandline.commandimpl.animal;

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
 * @author doyenm
 */
public class LsAnimal implements Command {

    @Override
    public ReturnExec execute(String[] cmd, Zoo zoo) {
        LsContext context = new LsContext(zoo);
        FormattingInList formatting = new FormattingInList();
        return new ReturnExec(formatting.formatList(context.getAnimalNames()), TypeReturn.SUCCESS);
    }

    @Override
    public boolean canExecute(String[] cmd) {
        return cmd.length == 1
                && Arrays.asList(Constants.ANIMALS).contains(cmd[0]);
    }

}
