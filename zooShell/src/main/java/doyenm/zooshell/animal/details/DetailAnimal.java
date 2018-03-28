package doyenm.zooshell.animal.details;

import doyenm.zooshell.commandline.general.Command;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.commandline.utils.FormattingInList;
import doyenm.zooshell.animal.AnimalContext;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.utils.Constants;
import doyenm.zooshell.animal.AnimalValidator;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

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
        boolean details = (cmd.length == 3)
                && Arrays.asList(Constants.ANIMAL)
                .stream()
                .anyMatch(cmd[0]::equalsIgnoreCase)
                && Arrays.asList(Constants.DETAILED)
                .stream()
                .anyMatch(cmd[2]::equalsIgnoreCase);
        return noDetails || details;
    }
}
