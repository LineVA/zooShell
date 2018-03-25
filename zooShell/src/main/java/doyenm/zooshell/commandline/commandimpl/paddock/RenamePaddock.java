package doyenm.zooshell.commandline.commandimpl.paddock;

import doyenm.zooshell.commandline.general.Command;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.context.PaddockChangeNameContext;
import doyenm.zooshell.controller.paddockcontroller.PaddockChangeNameController;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.utils.Constants;
import doyenm.zooshell.validator.PaddockChangeNameValidator;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

import lombok.RequiredArgsConstructor;

/**
 * @author doyenm
 */
@RequiredArgsConstructor
public class RenamePaddock implements Command {

    private final PaddockChangeNameValidator validator;
    private final PaddockChangeNameController controller;

    @Override
    public ReturnExec execute(String[] cmd, Zoo zoo) {
        PaddockChangeNameContext context = new PaddockChangeNameContext(zoo,
                cmd[2], cmd[3]);
        Optional<PaddockChangeNameContext> optional = Stream.of(context)
                .filter(validator)
                .map(controller)
                .findFirst();
        if (optional.isPresent()) {
            return new ReturnExec("PADDOCK_CHANGE_NAME_SUCCESS", TypeReturn.SUCCESS, context.getZoo());
        } else {
            return new ReturnExec("ERROR", TypeReturn.ERROR);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        return cmd.length == 4
                && Arrays.asList(Constants.PAD_OR_PADDOCK).contains(cmd[0])
                && Constants.RENAME.equalsIgnoreCase(cmd[1]);
    }

}
