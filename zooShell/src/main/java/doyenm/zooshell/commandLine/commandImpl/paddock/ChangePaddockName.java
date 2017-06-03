package doyenm.zooshell.commandLine.commandImpl.paddock;

import doyenm.zooshell.commandLine.general.AbstractCommand;
import doyenm.zooshell.commandLine.general.ReturnExec;
import doyenm.zooshell.commandLine.general.TypeReturn;
import doyenm.zooshell.context.PaddockChangeNameContext;
import doyenm.zooshell.controller.paddockcontroller.PaddockChangeNameController;
import doyenm.zooshell.launch.play.Play;
import doyenm.zooshell.utils.Constants;
import doyenm.zooshell.validator.PaddockChangeNameValidator;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 *
 * @author doyenm
 */
public class ChangePaddockName extends AbstractCommand {

    private final PaddockChangeNameController controller = new PaddockChangeNameController();
    private final PaddockChangeNameValidator validator = new PaddockChangeNameValidator();

    public ChangePaddockName(Play play) {
        super(play);
    }

    @Override
    public ReturnExec execute(String[] cmd) {
        try {
            PaddockChangeNameContext context = new PaddockChangeNameContext(this.getPlay().getZooModel(),
                    cmd[2], cmd[3]);
            context = Stream.of(context)
                    .filter(validator)
                    .map(controller)
                    .findFirst()
                    .get();
            getPlay().setZooModel(context.getZoo());
            setSuccess(true);
            return new ReturnExec("PADDOCK_CHANGE_NAME_SUCCESS", TypeReturn.SUCCESS);
        } catch (java.util.NoSuchElementException ex) {
            ex.printStackTrace();
            return new ReturnExec("ERROR", TypeReturn.ERROR);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 4) {
            if (Arrays.asList(Constants.PAD_OR_PADDOCK).contains(cmd[0])) {
                if (Constants.RENAME.equalsIgnoreCase(cmd[1])) {
                    return true;
                }
            }
        }
        return false;
    }

}
