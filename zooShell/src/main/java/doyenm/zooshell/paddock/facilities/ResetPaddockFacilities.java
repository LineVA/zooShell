package doyenm.zooshell.paddock.facilities;

import doyenm.zooshell.commandline.general.Command;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.paddock.PaddockContext;
import doyenm.zooshell.paddock.PaddockValidator;
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
public class ResetPaddockFacilities implements Command {

    private final PaddockValidator validator;
    private final ResetPaddockFacilitiesController controller;

    @Override
    public ReturnExec execute(String[] cmd, Zoo zoo) {
        PaddockContext context = new PaddockContext(zoo,
                cmd[2]);
        Optional<PaddockContext> optional = Stream.of(context)
                .map(validator)
                .filter(t -> t.getErrors().isEmpty())
                .map(controller)
                .findFirst();
        if (optional.isPresent()) {
            return new ReturnExec("RESET_PADDOCK_ARRANGEMENT_SUCCESS", TypeReturn.SUCCESS, context.getZoo());
        } else {
            return DisplayingErrorsList.displayErrorList(context.getErrors());
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        return cmd.length == 3
                && Arrays.asList(Constants.PAD_OR_PADDOCK_ARRANGEMENT)
                .stream()
                .anyMatch(cmd[0]::equalsIgnoreCase)
                && Arrays.asList(Constants.RESET)
                .stream()
                .anyMatch(cmd[1]::equalsIgnoreCase);
    }
}
