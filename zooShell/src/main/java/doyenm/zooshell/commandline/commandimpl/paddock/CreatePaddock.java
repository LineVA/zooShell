package doyenm.zooshell.commandline.commandimpl.paddock;

import doyenm.zooshell.commandline.general.Command;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.context.PaddockCreationContext;
import doyenm.zooshell.controller.paddockcontroller.PaddockCreationController;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.utils.Constants;
import doyenm.zooshell.validator.PaddockCreationValidator;
import doyenm.zooshell.validator.PaddockLocationValidator;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class CreatePaddock implements Command {

    private final PaddockCreationValidator valueValidator;
    private final PaddockLocationValidator locationValidator;
    private final PaddockCreationController controller;

    @Override
    public ReturnExec execute(String[] cmd, Zoo zoo) {
        PaddockCreationContext context = new PaddockCreationContext(zoo,
                cmd[2], cmd[3], cmd[4], cmd[5], cmd[6]);
        Optional<PaddockCreationContext> optionnal = Stream.of(context)
                .filter(valueValidator)
                .filter(locationValidator)
                .map(controller)
                .findFirst();
        if (optionnal.isPresent()) {
            return new ReturnExec("PADDOCK_CREATION_SUCCESS", TypeReturn.SUCCESS, context.getZoo());
        } else {
            return new ReturnExec("ERROR", TypeReturn.ERROR, context.getZoo());
        }
    }

    @Override
    public boolean canExecute(String[] cmd
    ) {
        if (cmd.length == 7) {
            if (Arrays.asList(Constants.PAD_OR_PADDOCK).contains(cmd[0])) {
                if (Constants.CREATE.equalsIgnoreCase(cmd[1])) {
                    return true;
                }
            }
        }
        return false;
    }

}
