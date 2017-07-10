package doyenm.zooshell.commandLine.commandImpl.keeper;

import doyenm.zooshell.commandLine.general.CommandBis;
import doyenm.zooshell.commandLine.general.ReturnExec;
import doyenm.zooshell.commandLine.general.TypeReturn;
import doyenm.zooshell.context.KeeperContext;
import doyenm.zooshell.controller.keepercontroller.KeeperDeletionController;
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
public class RemoveKeeper implements CommandBis{

    private final KeeperValidator validator;
    private final KeeperDeletionController controller;

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
            return new ReturnExec("KEEPERL_DELETION_SUCCESS", TypeReturn.SUCCESS, context.getZoo());
        } catch (java.util.NoSuchElementException ex) {
            ex.printStackTrace();
            return new ReturnExec("ERROR", TypeReturn.ERROR);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 3) {
            if (Arrays.asList(Constants.AK_OR_ANIMALKEEPER).contains(cmd[0])) {
                if (Constants.REMOVE.equalsIgnoreCase(cmd[1])) {
                    return true;
                }
            }
        }
        return false;
    }

}
