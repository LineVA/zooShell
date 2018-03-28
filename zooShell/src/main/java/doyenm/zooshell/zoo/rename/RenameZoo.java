package doyenm.zooshell.zoo.rename;

import doyenm.zooshell.commandline.general.Command;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.zoo.ZooContext;
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
public class RenameZoo implements Command {

    private final RenameZooController controller;

    @Override
    public ReturnExec execute(String[] cmd, Zoo zoo) {
        ZooContext context;
        context = new ZooContext(zoo, cmd[2]);
        Optional<ZooContext> optional = Stream.of(context)
                .map(controller)
                .findFirst();
        if (optional.isPresent()) {
            return new ReturnExec("ZOO_CHANGE_NAME_SUCCESS", TypeReturn.SUCCESS, context.getZoo());
        } else {
            return new ReturnExec("ERROR", TypeReturn.ERROR);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        return cmd.length == 3
                && Arrays.asList(Constants.ZOO)
                .stream()
                .anyMatch(cmd[0]::equalsIgnoreCase)
                && Arrays.asList(Constants.RENAME)
                .stream()
                .anyMatch(cmd[1]::equalsIgnoreCase);
    }
}
