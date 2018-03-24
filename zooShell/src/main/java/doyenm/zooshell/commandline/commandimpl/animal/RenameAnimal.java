package doyenm.zooshell.commandline.commandimpl.animal;

import doyenm.zooshell.commandline.general.Command;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.context.AnimalChangeNameContext;
import doyenm.zooshell.controller.animalcontroller.AnimalChangeNameController;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.utils.Constants;
import doyenm.zooshell.validator.AnimalChangeNameValidator;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class RenameAnimal implements Command {

    private final AnimalChangeNameValidator validator;
    private final AnimalChangeNameController controller;

    @Override
    public ReturnExec execute(String[] cmd, Zoo zoo) {
        AnimalChangeNameContext context = new AnimalChangeNameContext(zoo,
                cmd[2], cmd[3]);
        Optional optional = Stream.of(context)
                .filter(validator)
                .map(controller)
                .findFirst();
        if (optional.isPresent()) {
            return new ReturnExec("ANIMAL_CHANGE_NAME_SUCCESS", TypeReturn.SUCCESS, context.getZoo());
        } else {
            return new ReturnExec("ERROR", TypeReturn.ERROR);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 4) {
            if (Arrays.asList(Constants.ANIMAL).contains(cmd[0])) {
                if (Constants.RENAME.equalsIgnoreCase(cmd[1])) {
                    return true;
                }
            }
        }
        return false;
    }

}
