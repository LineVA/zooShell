package doyenm.zooshell.commandLine.commandImpl.paddock;

import doyenm.zooshell.commandLine.general.CommandBis;
import doyenm.zooshell.commandLine.general.ReturnExec;
import doyenm.zooshell.commandLine.general.TypeReturn;
import doyenm.zooshell.context.PaddockEntryCreationContext;
import doyenm.zooshell.controller.paddockcontroller.PaddockEntryCreationController;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.utils.Constants;
import doyenm.zooshell.validator.PaddockEntryCreationValidator;
import java.util.Arrays;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class CreatePaddockEntry implements CommandBis {

    private final PaddockEntryCreationValidator validator;
    private final PaddockEntryCreationController controller;

    @Override
    public ReturnExec execute(String[] cmd, Zoo zoo) {
        try {
            PaddockEntryCreationContext context = new PaddockEntryCreationContext(zoo,
                    cmd[2], cmd[3], cmd[4]);
            return Stream.of(context)
                    .map(validator)
                    .map(controller)
                    .map((PaddockEntryCreationContext t) -> t)
                    .map((PaddockEntryCreationContext t)
                            -> new ReturnExec("PADDOCK_ENTRY_CREATION_SUCCESS", TypeReturn.SUCCESS, t.getZoo()))
                    .findFirst()
                    .get();
        } catch (java.lang.NumberFormatException ex) {
            return new ReturnExec("NUMBER_FORMAT_EXCEPTION", TypeReturn.ERROR);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 5) {
            if (Arrays.asList(Constants.PAD_OR_PADDOCK_ENTRY).contains(cmd[0])) {
                if (Constants.CREATE.equalsIgnoreCase(cmd[1])) {
                    return true;
                }
            }
        }
        return false;
    }
}
