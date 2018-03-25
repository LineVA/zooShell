package doyenm.zooshell.commandline.commandimpl.keeper;

import doyenm.zooshell.commandline.general.Command;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.context.KeeperUpdateOccupationsContext;
import doyenm.zooshell.controller.keepercontroller.KeeperUpdateOccupationsController;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.utils.Constants;
import doyenm.zooshell.validator.KeeperUpdateOccupationsValidator;
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
            return new ReturnExec("KEEPER_UPATE_OCCUPATIONS_SUCCESS", TypeReturn.SUCCESS, context.getZoo());
        } else {
            return new ReturnExec("ERROR", TypeReturn.ERROR);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        return cmd.length == 6
                && Arrays.asList(Constants.AK_OR_ANIMALKEEPER_OCCUPATIONS).contains(cmd[0])
                && Constants.UPDATE.equalsIgnoreCase(cmd[1]);
    }

}