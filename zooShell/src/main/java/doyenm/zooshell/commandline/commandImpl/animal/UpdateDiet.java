package doyenm.zooshell.commandline.commandImpl.animal;

import doyenm.zooshell.commandline.general.Command;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.context.AnimalUpdateDietContext;
import doyenm.zooshell.controller.animalcontroller.AnimalUpdateDietController;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.utils.Constants;
import doyenm.zooshell.validator.AnimalUpdateDietValidator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;

/**
 *
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
        if (cmd.length >= 4) {
            if (Arrays.asList(Constants.ANIMAL_DIET).contains(cmd[0])) {
                if (Arrays.asList(Constants.UPDATE).contains(cmd[1])) {
                    return true;
                }
            }
        }
        return false;
    }

    private List<String> generateDietsList(String[] cmd) {
        List<String> list = new ArrayList<>();
        for (int i = 3; i < cmd.length; i++) {
            list.add(cmd[i]);
        }
        return list;
    }
}
