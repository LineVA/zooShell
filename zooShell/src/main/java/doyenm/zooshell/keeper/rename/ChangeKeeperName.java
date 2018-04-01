package doyenm.zooshell.keeper.rename;

import doyenm.zooshell.commandline.general.Command;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.utils.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author doyenm
 */
@Slf4j
@RequiredArgsConstructor
public class ChangeKeeperName implements Command {

    private final KeeperRenameValidator validator;
    private final KeeperRenameController controller;

    @Override
    public ReturnExec execute(String[] cmd, Zoo zoo) {
        KeeperRenameContext context = new KeeperRenameContext(zoo,
                cmd[2], cmd[3]);
        Optional<KeeperRenameContext> optional = Stream.of(context)
                .filter(validator)
                .map(controller)
                .findFirst();
        if (optional.isPresent()) {
            return new ReturnExec("KEEPER_CHANGE_NAME_SUCCESS", TypeReturn.SUCCESS, context.getZoo());
        } else {
            return new ReturnExec("ERROR", TypeReturn.ERROR);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        return cmd.length == 4
                && Arrays.asList(Constants.AK_OR_ANIMALKEEPER)
                .stream()
                .anyMatch(cmd[0]::equalsIgnoreCase)
                && Arrays.asList(Constants.RENAME)
                .stream()
                .anyMatch(cmd[1]::equalsIgnoreCase);
    }

}
