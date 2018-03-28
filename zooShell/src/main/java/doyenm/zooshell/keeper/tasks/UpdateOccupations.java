package doyenm.zooshell.keeper.tasks;

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
public class UpdateOccupations implements Command {

    private final KeeperUpdateOccupationsValidator validator;
    private final KeeperUpdateOccupationsController controller;

    @Override
    public ReturnExec execute(String[] cmd, Zoo zoo) {
        KeeperUpdateOccupationsContext context = new KeeperUpdateOccupationsContext(zoo,
                cmd[2], cmd[3], cmd[4], cmd[5]);
        Optional<KeeperUpdateOccupationsContext> optional = Stream.of(context)
                .filter(validator)
                .map(controller)
                .findFirst();
        if (optional.isPresent()) {
            return new ReturnExec("KEEPER_UPDATE_OCCUPATIONS_SUCCESS", TypeReturn.SUCCESS, context.getZoo());
        } else {
            return new ReturnExec("ERROR", TypeReturn.ERROR);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        return cmd.length == 6
                && Arrays.asList(Constants.AK_OR_ANIMALKEEPER_OCCUPATIONS)
                .stream()
                .anyMatch(cmd[0]::equalsIgnoreCase)
                && Arrays.asList(Constants.UPDATE)
                .stream()
                .anyMatch(cmd[1]::equalsIgnoreCase);
    }

}