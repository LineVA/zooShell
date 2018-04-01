package doyenm.zooshell.backup;

import doyenm.zooshell.backup.SaveFunction;
import doyenm.zooshell.commandline.general.Command;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.zoo.ZooContext;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.utils.Constants;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

/**
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
        return cmd.length == 2
                && Arrays.asList(Constants.SAVE)
                .stream()
                .anyMatch(cmd[0]::equalsIgnoreCase);
    }
}
