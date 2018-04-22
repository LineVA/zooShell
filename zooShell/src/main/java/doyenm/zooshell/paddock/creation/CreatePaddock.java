package doyenm.zooshell.paddock.creation;

import doyenm.zooshell.commandline.general.Command;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.utils.Constants;
import doyenm.zooshell.utils.DisplayingErrorsList;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author doyenm
 */
@RequiredArgsConstructor
public class CreatePaddock implements Command {

    private final PaddockCreationValidator valueValidator;
    private final PaddockLocationValidator locationValidator;
    private final PaddockCreationController controller;

    @Override
    public ReturnExec execute(String[] cmd, Zoo zoo) {
        PaddockCreationContext context = new PaddockCreationContext(zoo,
                cmd[2], cmd[3], cmd[4], cmd[5], cmd[6]);
        Optional<PaddockCreationContext> optionnal = Stream.of(context)
                .map(valueValidator)
                .filter(locationValidator)
                .filter(t -> t.getErrors().isEmpty())
                .map(controller)
                .findFirst();
        if (optionnal.isPresent()) {
            return new ReturnExec("PADDOCK_CREATION_SUCCESS", TypeReturn.SUCCESS, context.getZoo());
        } else {
            return DisplayingErrorsList.displayErrorList(context.getErrors());
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        return cmd.length == 7
                && Arrays.asList(Constants.PAD_OR_PADDOCK)
                .stream()
                .anyMatch(cmd[0]::equalsIgnoreCase)
                && Arrays.asList(Constants.CREATE)
                .stream()
                .anyMatch(cmd[1]::equalsIgnoreCase);
    }

}
