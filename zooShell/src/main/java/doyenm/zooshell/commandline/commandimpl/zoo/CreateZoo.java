package doyenm.zooshell.commandline.commandimpl.zoo;

import doyenm.zooshell.commandline.general.Command;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.context.ZooCreationContext;
import doyenm.zooshell.controller.zoocontroller.ZooCreationController;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.utils.Constants;
import doyenm.zooshell.validator.ZooCreationValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author doyenm
 */
@RequiredArgsConstructor
public class CreateZoo implements Command {

    @Autowired
    private final ZooCreationValidator validator;
    @Autowired
    private final ZooCreationController controller;

    private ZooCreationContext createContext(String[] cmd) {
        if (cmd.length == 3) {
            return new ZooCreationContext(cmd[2]);
        } else if (cmd.length == 5) {
            return new ZooCreationContext(cmd[2], cmd[3], cmd[4]);
        }
        return new ZooCreationContext(cmd[2], cmd[3], cmd[4], cmd[5], cmd[6]);
    }

    /**
     * zoo create
     *
     * @param cmd
     * @return
     */
    @Override
    public boolean canExecute(String[] cmd) {
        return (cmd.length == 3 || cmd.length == 5 || cmd.length == 7)
                && Constants.ZOO.equalsIgnoreCase(cmd[0])
                && Constants.CREATE.equalsIgnoreCase(cmd[1]);
    }

    @Override
    public ReturnExec execute(String[] cmd, Zoo zoo) {
        ZooCreationContext context = createContext(cmd);
        Optional<ZooCreationContext> optional = Stream.of(context)
                .filter(validator)
                .map(controller)
                .findFirst();
        if (optional.isPresent()) {
            return new ReturnExec("ZOO_CREATION_SUCESS", TypeReturn.SUCCESS, context.getZoo());
        } else {
            return new ReturnExec("Exception",
                    TypeReturn.ERROR, null);
        }
    }

}
