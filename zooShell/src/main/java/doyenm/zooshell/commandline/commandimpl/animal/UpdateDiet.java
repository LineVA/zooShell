package doyenm.zooshell.commandline.commandimpl.animal;

import doyenm.zooshell.commandline.general.Command;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.context.AnimalUpdateDietContext;
import doyenm.zooshell.controller.animalcontroller.AnimalUpdateDietController;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.utils.Constants;
import doyenm.zooshell.validator.AnimalUpdateDietValidator;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author doyenm
 */
@RequiredArgsConstructor
public class UpdateDiet implements Command {

    private final AnimalUpdateDietValidator validator;
    private final AnimalUpdateDietController controller;

    @Override
    public ReturnExec execute(String[] cmd, Zoo zoo) {
        AnimalUpdateDietContext context = new AnimalUpdateDietContext(zoo,
                cmd[2], generateDietsList(cmd));
        Optional optional = Stream.of(context)
                .filter(validator)
                .map(controller)
                .findFirst();
        if (optional.isPresent()) {
            return new ReturnExec("UPDATE_DIET_SUCCESS", TypeReturn.SUCCESS, context.getZoo());
        } else {
            return new ReturnExec("ERROR", TypeReturn.ERROR);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        return cmd.length >= 4
                && Arrays.asList(Constants.ANIMAL_DIET).contains(cmd[0])
                && Arrays.asList(Constants.UPDATE).contains(cmd[1]);
    }

    private List<String> generateDietsList(String[] cmd) {
        List<String> list = new ArrayList<>();
        for (int i = 3; i < cmd.length; i++) {
            list.add(cmd[i]);
        }
        return list;
    }
}
