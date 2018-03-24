package doyenm.zooshell.commandline.commandImpl;

import doyenm.zooshell.backup.SaveFunction;
import doyenm.zooshell.commandline.general.Command;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.context.ZooContext;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.utils.Constants;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class Save implements Command {

    private final SaveFunction controller;

    @Override
    public ReturnExec execute(String[] cmd, Zoo zoo) {
        ZooContext context = new ZooContext(zoo, cmd[1]);
        Optional optional = Stream.of(context)
                .map(controller)
                .filter(Objects::nonNull)
                .findFirst();
        if (optional.isPresent()) {
            return new ReturnExec("SUCCESSFUL_SAVING", TypeReturn.SUCCESS, zoo);
        }
        return new ReturnExec("FAILED_SAVING", TypeReturn.ERROR, zoo);
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 2) {
            return Arrays.asList(Constants.SAVE).contains(cmd[0]);
        }
        return false;
    }
}
