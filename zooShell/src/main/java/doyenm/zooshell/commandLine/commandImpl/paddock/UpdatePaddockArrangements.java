package doyenm.zooshell.commandLine.commandImpl.paddock;

import doyenm.zooshell.commandLine.general.Command;
import doyenm.zooshell.commandLine.general.ReturnExec;
import doyenm.zooshell.commandLine.general.TypeReturn;
import doyenm.zooshell.context.UpdatePaddockArrangementContext;
import doyenm.zooshell.controller.paddockcontroller.UpdatePaddockArrangementController;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.utils.Constants;
import doyenm.zooshell.validator.UpdatePaddockArrangementValidator;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;

/**
 *
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
        if (cmd.length == 4) {
            if (Arrays.asList(Constants.PAD_OR_PADDOCK_ARRANGEMENT).contains(cmd[0])) {
                if (Arrays.asList(Constants.UPDATE).contains(cmd[1])) {
                    return true;
                }
            }
        }
        return false;
    }
}
