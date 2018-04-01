package doyenm.zooshell.paddock.remove;

import doyenm.zooshell.commandline.general.Command;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.paddock.PaddockContext;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.utils.Constants;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author doyenm
 */
@RequiredArgsConstructor
public class RemovePaddock implements Command {

    private final PaddockRemoveValidator validator;
    private final PaddockRemoveController controller;

    @Override
    public ReturnExec execute(String[] cmd, Zoo zoo) {
        PaddockContext context = new PaddockContext(zoo,
                cmd[2]);
        Optional<PaddockContext> optional = Stream.of(context)
                .filter(validator)
                .map(controller)
                .findFirst();
        if (optional.isPresent()) {
            return new ReturnExec("PADDOCK_REMOVE_SUCCESS", TypeReturn.SUCCESS, context.getZoo());
        } else {
            return new ReturnExec("ERROR", TypeReturn.ERROR);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        return cmd.length == 3
                && Arrays.asList(Constants.PAD_OR_PADDOCK)
                .stream()
                .anyMatch(cmd[0]::equalsIgnoreCase)
                && Arrays.asList(Constants.REMOVE)
                .stream()
                .anyMatch(cmd[1]::equalsIgnoreCase);
    }

}