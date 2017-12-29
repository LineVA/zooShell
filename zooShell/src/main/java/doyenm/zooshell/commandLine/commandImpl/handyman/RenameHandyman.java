package doyenm.zooshell.commandLine.commandImpl.handyman;

import doyenm.zooshell.commandLine.general.Command;
import doyenm.zooshell.commandLine.general.ReturnExec;
import doyenm.zooshell.commandLine.general.TypeReturn;
import doyenm.zooshell.context.AnimalChangeNameContext;
import doyenm.zooshell.context.HandymanRenameContext;
import doyenm.zooshell.controller.animalcontroller.AnimalChangeNameController;
import doyenm.zooshell.controller.handymancontroller.RenamingController;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.utils.Constants;
import doyenm.zooshell.validator.AnimalChangeNameValidator;
import doyenm.zooshell.validator.HandymanRenameValidator;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class RenameHandyman implements Command {

    private final HandymanRenameValidator validator;
    private final RenamingController controller;

    @Override
    public ReturnExec execute(String[] cmd, Zoo zoo) {
            HandymanRenameContext context = new HandymanRenameContext(zoo,
                    cmd[2], cmd[3]);
            Optional optional = Stream.of(context)
                    .filter(validator)
                    .map(controller)
                    .findFirst();
            if (optional.isPresent()) {
                return new ReturnExec("HANDYMAN_CHANGE_NAME_SUCCESS", TypeReturn.SUCCESS, context.getZoo());
            } else {
                return new ReturnExec("ERROR", TypeReturn.ERROR);
            }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 4) {
            if (Arrays.asList(Constants.HANDYMAN_OR_HD).contains(cmd[0])) {
                if (Constants.RENAME.equalsIgnoreCase(cmd[1])) {
                    return true;
                }
            }
        }
        return false;
    }

}
