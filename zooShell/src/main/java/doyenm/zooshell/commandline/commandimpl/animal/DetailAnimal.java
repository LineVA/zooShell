package doyenm.zooshell.commandline.commandimpl.animal;

import doyenm.zooshell.commandline.general.Command;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.commandline.utils.FormattingInList;
import doyenm.zooshell.context.AnimalContext;
import doyenm.zooshell.controller.animalcontroller.AnimalDetailsController;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.utils.Constants;
import doyenm.zooshell.validator.AnimalValidator;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class DetailAnimal implements Command {

    private final AnimalValidator validator;
    private final AnimalDetailsController controller;

    @Override
    public ReturnExec execute(String[] cmd, Zoo zoo) {
        AnimalContext context = generateContext(cmd, zoo);
        Optional optional = Stream.of(context)
                .filter(validator)
                .map(controller)
                .findFirst();
        if (optional.isPresent()) {
            FormattingInList formatting = new FormattingInList();
            return new ReturnExec(formatting.format(context.getCouples()), TypeReturn.SUCCESS, context.getZoo());
        } else {
            return new ReturnExec("ERROR", TypeReturn.ERROR, context.getZoo());
        }
    }

    private AnimalContext generateContext(String[] cmd, Zoo zoo) {
        if (cmd.length == 3) {
            return new AnimalContext(zoo, cmd[1], true);
        } else {
            return new AnimalContext(zoo, cmd[1], false);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        boolean noDetails = cmd.length == 2 && Arrays.asList(Constants.ANIMAL).contains(cmd[0]);
        boolean details = cmd.length == 3
                && Arrays.asList(Constants.ANIMAL).contains(cmd[0])
                && Arrays.asList(Constants.DETAILED).contains(cmd[2]);
        return noDetails || details;
    }
}
