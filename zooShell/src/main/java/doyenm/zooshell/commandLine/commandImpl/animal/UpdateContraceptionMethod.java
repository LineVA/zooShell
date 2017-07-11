package doyenm.zooshell.commandLine.commandImpl.animal;

import doyenm.zooshell.commandLine.general.CommandBis;
import doyenm.zooshell.commandLine.general.ReturnExec;
import doyenm.zooshell.commandLine.general.TypeReturn;
import doyenm.zooshell.context.AnimalUpdateContraceptionContext;
import doyenm.zooshell.context.AnimalUpdateDietContext;
import doyenm.zooshell.controller.animalcontroller.AnimalUpdateContraceptionController;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.utils.Constants;
import doyenm.zooshell.validator.AnimalUpdateContraceptionValidator;
import java.util.Arrays;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class UpdateContraceptionMethod implements CommandBis{

    private final AnimalUpdateContraceptionValidator validator;
    private final AnimalUpdateContraceptionController controller;

    @Override
    public ReturnExec execute(String[] cmd, Zoo zoo) {
        try {
            AnimalUpdateContraceptionContext context = new AnimalUpdateContraceptionContext(zoo,
                    cmd[2], cmd[3]);
            context = Stream.of(context)
                    .filter(validator)
                    .map(controller)
                    .findFirst()
                    .get();
            return new ReturnExec("UPDATE_CONTRACEPTION_SUCCESS", TypeReturn.SUCCESS, context.getZoo());
        } catch (java.util.NoSuchElementException ex) {
            return new ReturnExec("ERROR", TypeReturn.ERROR);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 4) {
            if (Arrays.asList(Constants.ANIMAL_CONTRACEPTION).contains(cmd[0])) {
                if (Arrays.asList(Constants.UPDATE).contains(cmd[1])) {
                    return true;
                }
            }
        }
        return false;
    }
    
    
}