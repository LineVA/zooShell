package doyenm.zooshell.commandline.commandimpl.paddock;

import doyenm.zooshell.commandline.general.Command;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.context.PaddockExtensionCreationContext;
import doyenm.zooshell.controller.paddockcontroller.PaddockExtensionCreationController;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.utils.Constants;
import doyenm.zooshell.validator.PaddockExtensionCreationValidator;
import doyenm.zooshell.validator.PaddockExtensionLocationValidator;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class CreatePaddockExtension implements Command {

    private final PaddockExtensionCreationValidator creationValidator;
    private final PaddockExtensionLocationValidator locationValidator;
    private final PaddockExtensionCreationController controller;

    @Override
    public ReturnExec execute(String[] cmd, Zoo zoo) {
        PaddockExtensionCreationContext context = new PaddockExtensionCreationContext(zoo,
                cmd[2], cmd[3], cmd[4], cmd[5], cmd[6]);
        Optional<PaddockExtensionCreationContext> optional = Stream.of(context)
                .filter(creationValidator)
                .filter(locationValidator)
                .map(controller)
                .findFirst();
        if (optional.isPresent()) {
            return new ReturnExec("PADDOCK_EXTENSION_CREATION_SUCCESS", TypeReturn.SUCCESS, context.getZoo());
        } else {
            return new ReturnExec("ERROR", TypeReturn.ERROR, context.getZoo());
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
