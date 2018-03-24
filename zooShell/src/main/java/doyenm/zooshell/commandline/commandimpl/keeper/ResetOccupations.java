package doyenm.zooshell.commandline.commandimpl.keeper;

import doyenm.zooshell.commandline.general.Command;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.context.KeeperContext;
import doyenm.zooshell.controller.keepercontroller.KeeperResetOccupationsController;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.utils.Constants;
import doyenm.zooshell.validator.KeeperValidator;
import java.util.Arrays;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class ResetOccupations implements Command{

    private final KeeperValidator validator;
    private final KeeperResetOccupationsController controller;

    @Override
    public ReturnExec execute(String[] cmd, Zoo zoo) {
        try {
            KeeperContext context = new KeeperContext(zoo,
                    cmd[2], false);
            context = Stream.of(context)
                    .filter(validator)
                    .map(controller)
                    .findFirst()
                    .get();
            return new ReturnExec("KEEPER_RESET_OCCUPATIONS_SUCCESS", TypeReturn.SUCCESS, context.getZoo());
        } catch (java.util.NoSuchElementException ex) {
            ex.printStackTrace();
            return new ReturnExec("ERROR", TypeReturn.ERROR);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 3) {
            if (Arrays.asList(Constants.AK_OR_ANIMALKEEPER_OCCUPATIONS).contains(cmd[0])) {
                if (Constants.RESET.equalsIgnoreCase(cmd[1])) {
                    return true;
                }
            }
        }
        return false;
    }

}