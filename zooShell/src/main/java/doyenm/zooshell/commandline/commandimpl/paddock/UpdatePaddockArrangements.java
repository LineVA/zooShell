package doyenm.zooshell.commandline.commandimpl.paddock;

import doyenm.zooshell.commandline.general.Command;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.context.UpdatePaddockArrangementContext;
import doyenm.zooshell.controller.paddockcontroller.UpdatePaddockArrangementController;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.utils.Constants;
import doyenm.zooshell.validator.UpdatePaddockArrangementValidator;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author doyenm
 */
@RequiredArgsConstructor
public class UpdatePaddockArrangements implements Command {

    private final UpdatePaddockArrangementValidator validator;
    private final UpdatePaddockArrangementController controller;

    @Override
    public ReturnExec execute(String[] cmd, Zoo zoo) {
        UpdatePaddockArrangementContext context = new UpdatePaddockArrangementContext(zoo,
                cmd[2], cmd[3]);
        Optional<UpdatePaddockArrangementContext> optional = Stream.of(context)
                .filter(validator)
                .map(controller)
                .findFirst();
        if (optional.isPresent()) {
            return new ReturnExec("UPDATE_PADDOCK_ARRANGEMENT_SUCCESS", TypeReturn.SUCCESS, context.getZoo());
        } else {
            return new ReturnExec("ERROR", TypeReturn.ERROR);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        return cmd.length == 4
                && Arrays.asList(Constants.PAD_OR_PADDOCK_ARRANGEMENT)
                .stream()
                .anyMatch(cmd[0]::equalsIgnoreCase)
                && Arrays.asList(Constants.UPDATE)
                .stream()
                .anyMatch(cmd[1]::equalsIgnoreCase);
    }
}
