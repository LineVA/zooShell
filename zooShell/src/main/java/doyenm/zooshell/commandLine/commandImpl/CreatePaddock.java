package doyenm.zooshell.commandLine.commandImpl;

import doyenm.zooshell.commandLine.general.AbstractCommand;
import doyenm.zooshell.commandLine.general.ReturnExec;
import doyenm.zooshell.commandLine.general.TypeReturn;
import doyenm.zooshell.context.PaddockCreationContext;
import doyenm.zooshell.controller.paddockcontroller.PaddockCreationController;
import doyenm.zooshell.launch.play.Play;
import doyenm.zooshell.utils.Constants;
import doyenm.zooshell.validator.PaddockCreationValidator;
import doyenm.zooshell.validator.PaddockLocationValidator;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 *
 * @author doyenm
 */
public class CreatePaddock extends AbstractCommand {

    private PaddockCreationValidator valueValidator = new PaddockCreationValidator();
    private PaddockLocationValidator locationValidator = new PaddockLocationValidator();
    private PaddockCreationController controller = new PaddockCreationController();

    public CreatePaddock(Play play) {
        super(play);
    }

    @Override
    public ReturnExec execute(String[] cmd) {
        try {
            PaddockCreationContext context = new PaddockCreationContext(this.getPlay().getZooModel(),
                    cmd[2], cmd[3], cmd[4], cmd[5], cmd[6]);
            context = Stream.of(context)
                    .filter(valueValidator)
                    .filter(locationValidator)
                    .map(controller)
                    .findFirst()
                    .get();
            getPlay().setZooModel(context.getZoo());
            setSuccess(true);
            return new ReturnExec("PADDOCK_CREATION_SUCCESS", TypeReturn.SUCCESS);

//        } catch (NameException | IncorrectDimensionsException ex) {
//            return new ReturnExec(ex.getMessage(), TypeReturn.ERROR);
        } catch (java.lang.NumberFormatException ex) {
            return new ReturnExec("NUMBER_FORMAT_EXCEPTION", TypeReturn.ERROR);
        } catch (java.util.NoSuchElementException ex) {
            ex.printStackTrace();
            return new ReturnExec("ERROR", TypeReturn.ERROR);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 7) {
            if (Arrays.asList(Constants.PAD_OR_PADDOCK).contains(cmd[0])) {
                if (Constants.CREATE.equalsIgnoreCase(cmd[1])) {
                    return true;
                }
            }
        }
        return false;
    }

}
