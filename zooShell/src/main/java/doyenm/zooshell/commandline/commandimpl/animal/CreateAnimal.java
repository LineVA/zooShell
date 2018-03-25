package doyenm.zooshell.commandline.commandimpl.animal;

import doyenm.zooshell.commandline.general.Command;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.context.AnimalCreationContext;
import doyenm.zooshell.controller.animalcontroller.AnimalCreationController;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.utils.Constants;
import doyenm.zooshell.validator.AnimalCreationValidator;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author doyenm
 */
@RequiredArgsConstructor
public class CreateAnimal implements Command {

    private final AnimalCreationValidator validator;
    private final AnimalCreationController controller;

    @Override
    public ReturnExec execute(String[] cmd, Zoo zoo) {
        AnimalCreationContext context = new AnimalCreationContext(zoo,
                cmd[2], cmd[3], cmd[4], cmd[5]);
        Optional<AnimalCreationContext> optional = Stream.of(context)
                .filter(validator)
                .map(controller)
                .findFirst();
        if (optional.isPresent()) {
            return new ReturnExec("ANIMAL_CREATION_SUCCESS", TypeReturn.SUCCESS, context.getZoo());
        } else {
            return new ReturnExec("ERROR", TypeReturn.ERROR, context.getZoo());
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        return cmd.length == 6
                && Arrays.asList(Constants.ANIMAL).contains(cmd[0])
                && Constants.CREATE.equalsIgnoreCase(cmd[1]);
    }

}
