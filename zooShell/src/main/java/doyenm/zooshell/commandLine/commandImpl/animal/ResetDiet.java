package doyenm.zooshell.commandLine.commandImpl.animal;

import doyenm.zooshell.commandLine.general.Command;
import doyenm.zooshell.commandLine.general.ReturnExec;
import doyenm.zooshell.commandLine.general.TypeReturn;
import doyenm.zooshell.context.AnimalContext;
import doyenm.zooshell.controller.animalcontroller.AnimalResetDietController;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.utils.Constants;
import doyenm.zooshell.validator.AnimalValidator;
import java.util.Arrays;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class ResetDiet implements Command{

    private final AnimalValidator validator;
    private final AnimalResetDietController controller;

    @Override
    public ReturnExec execute(String[] cmd, Zoo zoo) {
        try {
            AnimalContext context = new AnimalContext(zoo,
                    cmd[2]);
            context = Stream.of(context)
                    .filter(validator)
                    .map(controller)
                    .findFirst()
                    .get();
            return new ReturnExec("RESET_DIET_SUCCESS", TypeReturn.SUCCESS, context.getZoo());
        } catch (java.util.NoSuchElementException ex) {
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
