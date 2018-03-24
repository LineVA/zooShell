package doyenm.zooshell.commandline.commandimpl.animal;

import doyenm.zooshell.commandline.general.Command;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.context.AnimalContext;
import doyenm.zooshell.controller.animalcontroller.AnimalResetDietController;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.utils.Constants;
import doyenm.zooshell.validator.AnimalValidator;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class ResetDiet implements Command {

    private final AnimalValidator validator;
    private final AnimalResetDietController controller;

    @Override
    public ReturnExec execute(String[] cmd, Zoo zoo) {
        AnimalContext context = new AnimalContext(zoo,
                cmd[2]);
        Optional optional = Stream.of(context)
                .filter(validator)
                .map(controller)
                .findFirst();
        if (optional.isPresent()) {
            return new ReturnExec("RESET_DIET_SUCCESS", TypeReturn.SUCCESS, context.getZoo());
        } else {
            return new ReturnExec("ERROR", TypeReturn.ERROR);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 3) {
            if (Arrays.asList(Constants.ANIMAL_DIET).contains(cmd[0])) {
                if (Arrays.asList(Constants.RESET).contains(cmd[1])) {
                    return true;
                }
            }
        }
        return false;
    }
}
