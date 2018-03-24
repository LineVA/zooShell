package doyenm.zooshell.commandline.commandimpl.animal;

import doyenm.zooshell.commandline.general.Command;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.context.AnimalUpdateFastDaysContext;
import doyenm.zooshell.controller.animalcontroller.AnimalUpdateFastDaysController;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.utils.Constants;
import doyenm.zooshell.validator.AnimalUpdateFastDaysValidator;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

import lombok.RequiredArgsConstructor;

/**
 * @author doyenm
 */
@RequiredArgsConstructor
public class UpdateFastDays implements Command {

    private final AnimalUpdateFastDaysValidator validator;
    private final AnimalUpdateFastDaysController controller;

    @Override
    public ReturnExec execute(String[] cmd, Zoo zoo) {
        AnimalUpdateFastDaysContext context = new AnimalUpdateFastDaysContext(zoo,
                cmd[2], cmd[3]);
        Optional optional = Stream.of(context)
                .filter(validator)
                .map(controller)
                .findFirst();
        if (optional.isPresent()) {
            return new ReturnExec("UPDATE_FAST_DAYS_SUCCESS", TypeReturn.SUCCESS, context.getZoo());
        } else {
            return new ReturnExec("ERROR", TypeReturn.ERROR);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        return cmd.length == 4
                && Arrays.asList(Constants.ANIMAL_FAST_DAYS).contains(cmd[0])
                && Arrays.asList(Constants.UPDATE).contains(cmd[1]);
    }
}
