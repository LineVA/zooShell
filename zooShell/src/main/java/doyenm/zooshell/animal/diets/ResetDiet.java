package doyenm.zooshell.animal.diets;

import doyenm.zooshell.commandline.general.Command;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.animal.AnimalContext;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.utils.Constants;
import doyenm.zooshell.animal.AnimalValidator;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

/**
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
        return cmd.length == 3
                && Arrays.asList(Constants.ANIMAL_DIET)
                .stream()
                .anyMatch(cmd[0]::equalsIgnoreCase)
                && Arrays.asList(Constants.RESET)
                .stream()
                .anyMatch(cmd[1]::equalsIgnoreCase);
    }
}