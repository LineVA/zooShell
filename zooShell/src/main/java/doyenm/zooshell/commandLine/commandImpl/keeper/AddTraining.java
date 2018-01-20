package doyenm.zooshell.commandLine.commandImpl.keeper;

import doyenm.zooshell.commandLine.general.Command;
import doyenm.zooshell.commandLine.general.ReturnExec;
import doyenm.zooshell.commandLine.general.TypeReturn;
import doyenm.zooshell.context.KeeperAddTrainingContext;
import doyenm.zooshell.context.KeeperUpdateOccupationsContext;
import doyenm.zooshell.controller.keepercontroller.KeeperAddTrainingController;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.utils.Constants;
import doyenm.zooshell.validator.KeeperAddTrainingValidator;
import java.util.Arrays;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class AddTraining implements Command{

    private final KeeperAddTrainingValidator validator;
    private final KeeperAddTrainingController controller;

    @Override
    public ReturnExec execute(String[] cmd, Zoo zoo) {
        try {
            KeeperAddTrainingContext context = new KeeperAddTrainingContext(zoo,
                    cmd[2], cmd[3]);
            context = Stream.of(context)
                    .filter(validator)
                    .map(controller)
                    .findFirst()
                    .get();
            return new ReturnExec("KEEPER_ADD_TRAINING_SUCCESS", TypeReturn.SUCCESS, context.getZoo());
        } catch (java.util.NoSuchElementException ex) {
            ex.printStackTrace();
            return new ReturnExec("ERROR", TypeReturn.ERROR);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 4) {
            if (Arrays.asList(Constants.AK_OR_ANIMALKEEPER_TRAINING).contains(cmd[0])) {
                if (Constants.UPDATE.equalsIgnoreCase(cmd[1])) {
                    return true;
                }
            }
        }
        return false;
    }

}