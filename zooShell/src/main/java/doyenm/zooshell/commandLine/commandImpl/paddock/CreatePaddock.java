package doyenm.zooshell.commandLine.commandImpl.paddock;

import doyenm.zooshell.commandLine.general.Command;
import doyenm.zooshell.commandLine.general.ReturnExec;
import doyenm.zooshell.commandLine.general.TypeReturn;
import doyenm.zooshell.context.PaddockCreationContext;
import doyenm.zooshell.controller.paddockcontroller.PaddockCreationController;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.utils.Constants;
import doyenm.zooshell.validator.PaddockCreationValidator;
import doyenm.zooshell.validator.PaddockLocationValidator;
import java.util.Arrays;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class CreatePaddock implements Command{

    private final PaddockCreationValidator valueValidator;
    private final PaddockLocationValidator locationValidator;
    private final PaddockCreationController controller;

    @Override
    public ReturnExec execute(String[] cmd, Zoo zoo) {
        try {
            PaddockCreationContext context = new PaddockCreationContext(zoo,
                    cmd[2], cmd[3], cmd[4], cmd[5], cmd[6]);
            context = Stream.of(context)
                    .filter(valueValidator)
                    .filter(locationValidator)
                    .map(controller)
                    .findFirst()
                    .get();
            return new ReturnExec("PADDOCK_CREATION_SUCCESS", TypeReturn.SUCCESS, context.getZoo());

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
