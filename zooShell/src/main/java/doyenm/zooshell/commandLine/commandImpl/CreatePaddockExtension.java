package doyenm.zooshell.commandLine.commandImpl;

import doyenm.zooshell.commandLine.general.AbstractCommand;
import doyenm.zooshell.commandLine.general.ReturnExec;
import doyenm.zooshell.commandLine.general.TypeReturn;
import doyenm.zooshell.context.PaddockExtensionCreationContext;
import doyenm.zooshell.controller.paddockcontroller.PaddockExtensionCreationController;
import doyenm.zooshell.launch.play.Play;
import doyenm.zooshell.utils.Constants;
import doyenm.zooshell.validator.PaddockExtensionCreationValidator;
import doyenm.zooshell.validator.PaddockExtensionLocationValidator;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 *
 * @author doyenm
 */
public class CreatePaddockExtension extends AbstractCommand {

    private PaddockExtensionCreationValidator creationValidator = new PaddockExtensionCreationValidator();
    private PaddockExtensionCreationController controller = new PaddockExtensionCreationController();
    private PaddockExtensionLocationValidator locationValidator = new PaddockExtensionLocationValidator();

    public CreatePaddockExtension(Play play) {
        super(play);
    }

    @Override
    public ReturnExec execute(String[] cmd) {
        try {
            PaddockExtensionCreationContext context = new PaddockExtensionCreationContext(this.getPlay().getZooModel(),
                    cmd[2], cmd[3], cmd[4], cmd[5], cmd[6]);
            context = Stream.of(context)
                    .filter(creationValidator)
                    .filter(locationValidator)
                    .map(controller)
                    .findFirst()
                    .get();
            getPlay().setZooModel(context.getZoo());
            setSuccess(true);
            return new ReturnExec("PADDOCK_EXTENSION_CREATION_SUCCESS", TypeReturn.SUCCESS);
        } catch (java.lang.NumberFormatException ex) {
            return new ReturnExec(
                    super.getPlay().getOption().getGeneralCmdBundle()
                    .getString("NUMBER_FORMAT_EXCEPTION"), TypeReturn.ERROR);
        } catch (java.util.NoSuchElementException ex) {
            return new ReturnExec("ERROR", TypeReturn.ERROR);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 7) {
            if (Arrays.asList(Constants.PAD_OR_PADDOCK_EXTENSION).contains(cmd[0])) {
                if (Constants.CREATE.equalsIgnoreCase(cmd[1])) {
                    return true;
                }
            }
        }
        return false;
    }
}
