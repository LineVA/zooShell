package doyenm.zooshell.commandLine.commandImpl.handyman;

import doyenm.zooshell.commandLine.general.Command;
import doyenm.zooshell.commandLine.general.ReturnExec;
import doyenm.zooshell.commandLine.general.TypeReturn;
import doyenm.zooshell.context.HandymanCreationContext;
import doyenm.zooshell.controller.handymancontroller.CreationController;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.utils.Constants;
import doyenm.zooshell.validator.HandymanCreationValidator;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class CreateHandyman implements Command {

    private final HandymanCreationValidator validator;
    private final CreationController controller;

    @Override
    public ReturnExec execute(String[] cmd, Zoo zoo) {
        try {
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
        } catch (java.util.NoSuchElementException ex) {
            ex.printStackTrace();
            return new ReturnExec("ERROR", TypeReturn.ERROR);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 3) {
            if (Arrays.asList(Constants.HANDYMAN_OR_HD).contains(cmd[0])) {
                if (Constants.CREATE.equalsIgnoreCase(cmd[1])) {
                    return true;
                }
            }
        }
        return false;
    }

}
