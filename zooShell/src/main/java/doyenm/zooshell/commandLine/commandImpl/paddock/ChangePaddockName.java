package doyenm.zooshell.commandLine.commandImpl.paddock;

import doyenm.zooshell.commandLine.general.CommandBis;
import doyenm.zooshell.commandLine.general.ReturnExec;
import doyenm.zooshell.commandLine.general.TypeReturn;
import doyenm.zooshell.context.PaddockChangeNameContext;
import doyenm.zooshell.controller.paddockcontroller.PaddockChangeNameController;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.utils.Constants;
import doyenm.zooshell.validator.PaddockChangeNameValidator;
import java.util.Arrays;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class ChangePaddockName implements CommandBis {

    private final PaddockChangeNameValidator validator;
    private final PaddockChangeNameController controller;

    @Override
    public ReturnExec execute(String[] cmd, Zoo zoo) {
        try {
            PaddockChangeNameContext context = new PaddockChangeNameContext(zoo,
                    cmd[2], cmd[3]);
            context = Stream.of(context)
                    .filter(validator)
                    .map(controller)
                    .findFirst()
                    .get();
            return new ReturnExec("PADDOCK_CHANGE_NAME_SUCCESS", TypeReturn.SUCCESS, context.getZoo());
        } catch (java.util.NoSuchElementException ex) {
            ex.printStackTrace();
            return new ReturnExec("ERROR", TypeReturn.ERROR);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 4) {
            if (Arrays.asList(Constants.PAD_OR_PADDOCK).contains(cmd[0])) {
                if (Constants.RENAME.equalsIgnoreCase(cmd[1])) {
                    return true;
                }
            }
        }
        return false;
    }

}
