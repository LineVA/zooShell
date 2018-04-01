package doyenm.zooshell.handyman.rename;

import doyenm.zooshell.commandline.general.Command;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
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
public class RenameHandyman implements Command {

    private final HandymanRenameValidator validator;
    private final RenamingController controller;

    @Override
    public ReturnExec execute(String[] cmd, Zoo zoo) {
        HandymanRenameContext context = new HandymanRenameContext(zoo,
                cmd[2], cmd[3]);
        Optional optional = Stream.of(context)
                .filter(validator)
                .map(controller)
                .findFirst();
        if (optional.isPresent()) {
            return new ReturnExec("HANDYMAN_CHANGE_NAME_SUCCESS", TypeReturn.SUCCESS, context.getZoo());
        } else {
            return new ReturnExec("ERROR", TypeReturn.ERROR);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        return cmd.length == 4
                && Arrays.asList(Constants.HANDYMAN_OR_HD)
                .stream()
                .anyMatch(cmd[0]::equalsIgnoreCase)
                && Arrays.asList(Constants.RENAME)
                .stream()
                .anyMatch(cmd[1]::equalsIgnoreCase);
    }

}