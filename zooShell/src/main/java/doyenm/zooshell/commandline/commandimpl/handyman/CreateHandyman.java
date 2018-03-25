package doyenm.zooshell.commandline.commandimpl.handyman;

import doyenm.zooshell.commandline.general.Command;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.context.HandymanCreationContext;
import doyenm.zooshell.controller.handymancontroller.CreationController;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.utils.Constants;
import doyenm.zooshell.validator.HandymanCreationValidator;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author doyenm
 */
@RequiredArgsConstructor
public class CreateHandyman implements Command {

    private final HandymanCreationValidator validator;
    private final CreationController controller;

    @Override
    public ReturnExec execute(String[] cmd, Zoo zoo) {
            HandymanCreationContext context = new HandymanCreationContext(zoo,
                    cmd[2]);
            Optional<HandymanCreationContext> optional = Stream.of(context)
                    .filter(validator)
                    .map(controller)
                    .findFirst();
            if (optional.isPresent()) {
                return new ReturnExec("HANDYMAN_CREATION_SUCCESS", TypeReturn.SUCCESS, context.getZoo());
            } else {
                return new ReturnExec("ERROR", TypeReturn.ERROR, context.getZoo());
            }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        return cmd.length == 3
                && Arrays.asList(Constants.HANDYMAN_OR_HD).contains(cmd[0])
                && Constants.CREATE.equalsIgnoreCase(cmd[1]);
    }

}
