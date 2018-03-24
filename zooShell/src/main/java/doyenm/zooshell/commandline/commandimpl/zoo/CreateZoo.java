package doyenm.zooshell.commandline.commandimpl.zoo;

import doyenm.zooshell.commandline.general.Command;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.controller.zoocontroller.ZooCreationController;
import doyenm.zooshell.utils.Constants;
import doyenm.zooshell.context.ZooCreationContext;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.validator.ZooCreationValidator;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
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
//    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 3 || cmd.length == 5 || cmd.length == 7) {
            if (Constants.ZOO.equalsIgnoreCase(cmd[0]) && Constants.CREATE.equalsIgnoreCase(cmd[1])) {
                return true;
            }
        }
        return false;
    }

    public ReturnExec execute(String[] cmd, Zoo zoo) {
        try {
            ZooCreationContext context = createContext(cmd);
            context = Stream.of(context)
                    .filter(validator)
                    .map(controller)
                    .findFirst()
                    .get();
            return new ReturnExec("ZOO_CREATION_SUCESS", TypeReturn.SUCCESS, context.getZoo());

        } catch (java.lang.NumberFormatException ex) {
            return new ReturnExec("Exception",
                    TypeReturn.ERROR, null);
        } catch (java.util.NoSuchElementException ex) {
            return new ReturnExec("ERROR", TypeReturn.ERROR, null);
        }
    }

}
