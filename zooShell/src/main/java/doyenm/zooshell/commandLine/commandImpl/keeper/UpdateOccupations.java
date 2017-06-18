package doyenm.zooshell.commandLine.commandImpl.keeper;

import doyenm.zooshell.commandLine.general.CommandBis;
import doyenm.zooshell.commandLine.general.ReturnExec;
import doyenm.zooshell.commandLine.general.TypeReturn;
import doyenm.zooshell.context.KeeperUpdateOccupationsContext;
import doyenm.zooshell.controller.keepercontroller.KeeperUpdateOccupationsController;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.utils.Constants;
import doyenm.zooshell.validator.KeeperUpdateOccupationsValidator;
import java.util.Arrays;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class UpdateOccupations implements CommandBis{

    private final KeeperUpdateOccupationsValidator validator;
    private final KeeperUpdateOccupationsController controller;

    @Override
    public ReturnExec execute(String[] cmd, Zoo zoo) {
        try {
            KeeperUpdateOccupationsContext context = new KeeperUpdateOccupationsContext(zoo,
                    cmd[2], cmd[3], cmd[4], cmd[5]);
            context = Stream.of(context)
                    .filter(validator)
                    .map(controller)
                    .findFirst()
                    .get();
            return new ReturnExec("KEEPER_UPATE_OCCUPATIONS_SUCCESS", TypeReturn.SUCCESS, context.getZoo());
        } catch (java.util.NoSuchElementException ex) {
            ex.printStackTrace();
            return new ReturnExec("ERROR", TypeReturn.ERROR);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 6) {
            if (Arrays.asList(Constants.AK_OR_ANIMALKEEPER_OCCUPATIONS).contains(cmd[0])) {
                if (Constants.UPDATE.equalsIgnoreCase(cmd[1])) {
                    return true;
                }
            }
        }
        return false;
    }

}