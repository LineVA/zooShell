package doyenm.zooshell.commandline.commandImpl.keeper;

import doyenm.zooshell.commandline.general.Command;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.context.KeeperAddTrainingContext;
import doyenm.zooshell.context.KeeperUpdateOccupationsContext;
import doyenm.zooshell.controller.keepercontroller.KeeperAddTrainingController;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.utils.Constants;
import doyenm.zooshell.validator.KeeperAddTrainingValidator;
import java.util.Arrays;
import java.util.Optional;
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
            KeeperAddTrainingContext context = new KeeperAddTrainingContext(zoo,
                    cmd[2], cmd[3]);
            Optional optional = Stream.of(context)
                    .filter(validator)
                    .map(controller)
                    .findFirst();
           if (optional.isPresent()) {
                return new ReturnExec("HANDYMAN_REMOVE_SUCCESS", TypeReturn.SUCCESS, context.getZoo());
            } else {
                return new ReturnExec("ERROR", TypeReturn.ERROR, context.getZoo());
            }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        return cmd.length == 4
                && Arrays.asList(Constants.AK_OR_ANIMALKEEPER_TRAINING).contains(cmd[0])
                && Constants.UPDATE.equalsIgnoreCase(cmd[1]);
    }

}