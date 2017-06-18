package doyenm.zooshell.commandLine.commandImpl.animal;

import doyenm.zooshell.commandLine.general.CommandBis;
import doyenm.zooshell.commandLine.general.ReturnExec;
import doyenm.zooshell.commandLine.general.TypeReturn;
import doyenm.zooshell.context.AnimalCreationContext;
import doyenm.zooshell.controller.animalcontroller.AnimalCreationController;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.utils.Constants;
import doyenm.zooshell.validator.AnimalCreationValidator;
import java.util.Arrays;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class CreateAnimal implements CommandBis {

    private final AnimalCreationValidator validator;
    private final AnimalCreationController controller;
    
    @Override
    public ReturnExec execute(String[] cmd, Zoo zoo) {
        try {
            AnimalCreationContext context = new AnimalCreationContext(zoo,
                    cmd[2], cmd[3], cmd[4], cmd[5]);
            context = Stream.of(context)
                    .filter(validator)
                    .map(controller)
                    .findFirst()
                    .get();
            return new ReturnExec("ANIMAL_CREATION_SUCCESS", TypeReturn.SUCCESS, context.getZoo());
        } catch (java.util.NoSuchElementException ex) {
            ex.printStackTrace();
            return new ReturnExec("ERROR", TypeReturn.ERROR);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 6) {
            if (Arrays.asList(Constants.ANIMAL).contains(cmd[0])) {
                if (Constants.CREATE.equalsIgnoreCase(cmd[1])) {
                    return true;
                }
            }
        }
        return false;
    }

}
