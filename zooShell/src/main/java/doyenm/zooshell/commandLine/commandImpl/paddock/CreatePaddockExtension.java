 package doyenm.zooshell.commandLine.commandImpl.paddock;

import doyenm.zooshell.commandLine.general.CommandBis;
import doyenm.zooshell.commandLine.general.ReturnExec;
import doyenm.zooshell.commandLine.general.TypeReturn;
import doyenm.zooshell.context.PaddockExtensionCreationContext;
import doyenm.zooshell.controller.paddockcontroller.PaddockExtensionCreationController;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.utils.Constants;
import doyenm.zooshell.validator.PaddockExtensionCreationValidator;
import doyenm.zooshell.validator.PaddockExtensionLocationValidator;
import java.util.Arrays;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class CreatePaddockExtension implements CommandBis {

    private final PaddockExtensionCreationValidator creationValidator;
    private final PaddockExtensionLocationValidator locationValidator;
    private final PaddockExtensionCreationController controller;

    @Override
    public ReturnExec execute(String[] cmd, Zoo zoo) {
        try {
            PaddockExtensionCreationContext context = new PaddockExtensionCreationContext(zoo,
                    cmd[2], cmd[3], cmd[4], cmd[5], cmd[6]);
            context = Stream.of(context)
                    .filter(creationValidator)
                    .filter(locationValidator)
                    .map(controller)
                    .findFirst()
                    .get();
            return new ReturnExec("PADDOCK_EXTENSION_CREATION_SUCCESS", TypeReturn.SUCCESS, context.getZoo());
        } catch (java.lang.NumberFormatException ex) {
            return new ReturnExec(
                    "NUMBER_FORMAT_EXCEPTION", TypeReturn.ERROR);
        } catch (java.util.NoSuchElementException ex) {
            return new ReturnExec("ERROR", TypeReturn.ERROR);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 7) {
            if (Arrays.asList(Constants.PAD_OR_PADDOCK_EXTENSION).contains(cmd[0])) {
                if (Constants.CREATE.equalsIgnoreCase(cmd[1])) {
                    return true;
                }
            }
        }
        return false;
    }
}
