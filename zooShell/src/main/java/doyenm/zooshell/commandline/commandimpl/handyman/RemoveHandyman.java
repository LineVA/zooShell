package doyenm.zooshell.commandline.commandimpl.handyman;

import doyenm.zooshell.commandline.general.Command;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.context.HandymanContext;
import doyenm.zooshell.context.HandymanRenameContext;
import doyenm.zooshell.controller.handymancontroller.RemovingController;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.utils.Constants;
import doyenm.zooshell.validator.HandymanValidator;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class RemoveHandyman  implements Command {

    private final HandymanValidator validator;
    private final RemovingController controller;

    @Override
    public ReturnExec execute(String[] cmd, Zoo zoo) {
            HandymanContext context = new HandymanContext(zoo,
                    cmd[2]);
            Optional optional = Stream.of(context)
                    .filter(validator)
                    .map(controller)
                    .findFirst();
            if (optional.isPresent()) {
                return new ReturnExec("HANDYMAN_REMOVE_SUCCESS", TypeReturn.SUCCESS, context.getZoo());
            } else {
                return new ReturnExec("ERROR", TypeReturn.ERROR);
            }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 3) {
            if (Arrays.asList(Constants.HANDYMAN_OR_HD).contains(cmd[0])) {
                if (Constants.REMOVE.equalsIgnoreCase(cmd[1])) {
                    return true;
                }
            }
        }
        return false;
    }

}
