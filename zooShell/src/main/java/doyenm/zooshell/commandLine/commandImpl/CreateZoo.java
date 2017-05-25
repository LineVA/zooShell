package doyenm.zooshell.commandLine.commandImpl;

import doyenm.zooshell.commandLine.general.AbstractChangeZooCommand;
import doyenm.zooshell.commandLine.general.ReturnExec;
import doyenm.zooshell.commandLine.general.TypeReturn;
import doyenm.zooshell.controller.zoocontroller.ZooCreationController;
import doyenm.zooshell.launch.play.Play;
import doyenm.zooshell.utils.Constants;
import doyenm.zooshell.context.ZooCreationContext;
import doyenm.zooshell.validator.ZooCreationValidator;
import java.util.stream.Stream;

/**
 *
 * @author doyenm
 */
public class CreateZoo extends AbstractChangeZooCommand {

    private final ZooCreationValidator validator = new ZooCreationValidator();
    private final ZooCreationController controller = new ZooCreationController();

    public CreateZoo(Play play) {
        super(play);
    }

    private ZooCreationContext createContext(String[] cmd) {
        if (cmd.length == 3) {
            return new ZooCreationContext(cmd[2]);
        } else if (cmd.length == 5) {
            return new ZooCreationContext(cmd[2], cmd[3], cmd[4]);
        }
        return new ZooCreationContext(cmd[2], cmd[3], cmd[4], cmd[5], cmd[6]);
    }

    @Override
    public ReturnExec executeChanging(String[] cmd) {
        try {
            ZooCreationContext context = createContext(cmd);
            context = Stream.of(context)
                    .filter(validator)
                    .map(controller)
                    .findFirst()
                    .get();
            getPlay().setZooModel(context.getZoo());
            setChangingZoo(false);
            setInitiate(true);
            setSuccess(true);
//                            return new ReturnExec(getPlay().getOption().getGeneralCmdBundle()
//                                    .getString("ZOO_CREATION_SUCESS"), TypeReturn.SUCCESS);
            return new ReturnExec("ZOO_CREATION_SUCESS", TypeReturn.SUCCESS);

        } catch (java.lang.NumberFormatException ex) {
            return new ReturnExec(
                    super.getPlay().getOption().getGeneralCmdBundle()
                    .getString("NUMBER_FORMAT_EXCEPTION"), TypeReturn.ERROR);
        } catch (java.util.NoSuchElementException ex){
            return new ReturnExec("ERROR", TypeReturn.ERROR);
        }
    }

    /**
     * zoo create
     *
     * @param cmd
     * @return
     */
    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 3 || cmd.length == 5 || cmd.length == 7) {
            if (Constants.ZOO.equalsIgnoreCase(cmd[0]) && Constants.CREATE.equalsIgnoreCase(cmd[1])) {
                return true;
            }
        }
        return false;
    }

}
