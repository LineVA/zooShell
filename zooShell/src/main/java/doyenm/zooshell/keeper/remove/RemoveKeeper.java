package doyenm.zooshell.keeper.remove;

import doyenm.zooshell.commandline.general.Command;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.keeper.KeeperContext;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.utils.Constants;
import doyenm.zooshell.keeper.KeeperValidator;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author doyenm
 */
@RequiredArgsConstructor
public class RemoveKeeper implements Command {

    private final KeeperValidator validator;
    private final KeeperDeletionController controller;

    @Override
    public ReturnExec execute(String[] cmd, Zoo zoo) {
        KeeperContext context = new KeeperContext(zoo,
                cmd[2], false);
        Optional<KeeperContext> optional = Stream.of(context)
                .filter(validator)
                .map(controller)
                .findFirst();
        if (optional.isPresent()) {
            return new ReturnExec("KEEPERL_DELETION_SUCCESS", TypeReturn.SUCCESS, context.getZoo());
        } else {
            return new ReturnExec("ERROR", TypeReturn.ERROR);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        return cmd.length == 3
                && Arrays.asList(Constants.AK_OR_ANIMALKEEPER)
                .stream()
                .anyMatch(cmd[0]::equalsIgnoreCase)
                && Arrays.asList(Constants.REMOVE)
                .stream()
                .anyMatch(cmd[1]::equalsIgnoreCase);
    }

}
