package doyenm.zooshell.commandline.commandimpl.keeper;

import doyenm.zooshell.commandline.general.Command;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.context.KeeperCreationContext;
import doyenm.zooshell.controller.keepercontroller.KeeperCreationController;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.utils.Constants;
import doyenm.zooshell.validator.KeeperCreationValidator;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

import lombok.RequiredArgsConstructor;

/**
 * @author doyenm
 */
@RequiredArgsConstructor
public class CreateKeeper implements Command {

    private final KeeperCreationValidator validator;
    private final KeeperCreationController controller;

    @Override
    public ReturnExec execute(String[] cmd, Zoo zoo) {
            KeeperCreationContext context = new KeeperCreationContext(zoo,
                    cmd[2]);
        Optional<KeeperCreationContext> optional = Stream.of(context)
                    .filter(validator)
                    .map(controller)
                    .findFirst();
        if(optional.isPresent()){
            return new ReturnExec("KEEPERL_CREATION_SUCCESS", TypeReturn.SUCCESS, context.getZoo());
        } else {
            return new ReturnExec("ERROR", TypeReturn.ERROR);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        return cmd.length == 3
                && Arrays.asList(Constants.AK_OR_ANIMALKEEPER).contains(cmd[0])
                && Constants.CREATE.equalsIgnoreCase(cmd[1]);
    }

}
