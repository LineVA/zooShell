package doyenm.zooshell.commandline.commandimpl.animal;

import doyenm.zooshell.commandline.general.Command;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.context.AnimalChangePaddockContext;
import doyenm.zooshell.controller.animalcontroller.AnimalChangePaddockController;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.utils.Constants;
import doyenm.zooshell.validator.AnimalChangePaddockValidator;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class ChangePaddock implements Command {

    private final AnimalChangePaddockValidator validator;
    private final AnimalChangePaddockController controller;

    @Override
    public ReturnExec execute(String[] cmd, Zoo zoo) {
            AnimalChangePaddockContext context = new AnimalChangePaddockContext(zoo,
                    cmd[2], cmd[3]);
            Optional optional = Stream.of(context)
                    .filter(validator)
                    .map(controller)
                    .findFirst();
            if(optional.isPresent()){
            return new ReturnExec("ANIMAL_CHANGE_PADDOCK_SUCCESS", TypeReturn.SUCCESS, context.getZoo());
            } else {
            return new ReturnExec("ERROR", TypeReturn.ERROR);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 4) {
            if (Arrays.asList(Constants.ANIMAL_PADDOCK).contains(cmd[0])) {
                if (Constants.UPDATE.equalsIgnoreCase(cmd[1])) {
                    return true;
                }
            }
        }
        return false;
    }

}
