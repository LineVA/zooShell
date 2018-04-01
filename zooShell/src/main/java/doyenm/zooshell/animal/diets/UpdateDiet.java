package doyenm.zooshell.animal.diets;

import doyenm.zooshell.commandline.general.Command;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.utils.Constants;
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
                && Arrays.asList(Constants.ANIMAL_DIET)
                .stream()
                .anyMatch(cmd[0]::equalsIgnoreCase)
                && Arrays.asList(Constants.UPDATE)
                .stream()
                .anyMatch(cmd[1]::equalsIgnoreCase);
    }

    private List<String> generateDietsList(String[] cmd) {
        List<String> list = new ArrayList<>();
        for (int i = 3; i < cmd.length; i++) {
            list.add(cmd[i]);
        }
        return list;
    }
}
