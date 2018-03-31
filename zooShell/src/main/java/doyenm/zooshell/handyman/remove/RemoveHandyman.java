package doyenm.zooshell.handyman.remove;

import doyenm.zooshell.commandline.general.Command;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.handyman.HandymanContext;
import doyenm.zooshell.handyman.occupations.UpdateOccupations;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.utils.Constants;
import doyenm.zooshell.handyman.HandymanValidator;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author doyenm
 */
@RequiredArgsConstructor
public class RemoveHandyman implements Command {

    private final HandymanValidator validator;
    private final UpdateOccupations.RemovingController controller;

    @Override
    public ReturnExec execute(String[] cmd, Zoo zoo) {
        HandymanContext context = new HandymanContext(zoo,
                cmd[2]);
        Optional optional = Stream.of(context)
                .filter(validator)
                .map(controller)
                .findFirst();
        if (optional.isPresent()) {
            return new ReturnExec("HANDYMAN_REMOVE_SUCCESS", TypeReturn.SUCCESS, context.getZoo());
        } else {
            return new ReturnExec("ERROR", TypeReturn.ERROR);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        return cmd.length == 3
                && Arrays.asList(Constants.HANDYMAN_OR_HD)
                .stream()
                .anyMatch(cmd[0]::equalsIgnoreCase)
                && Arrays.asList(Constants.REMOVE)
                .stream()
                .anyMatch(cmd[1]::equalsIgnoreCase);
    }

}
