package doyenm.zooshell.commandLine.commandImpl.animal;

import doyenm.zooshell.commandLine.general.AbstractCommand;
import doyenm.zooshell.commandLine.general.ReturnExec;
import doyenm.zooshell.commandLine.general.TypeReturn;
import doyenm.zooshell.context.AnimalChangePaddockContext;
import doyenm.zooshell.context.AnimalCreationContext;
import doyenm.zooshell.controller.animalcontroller.AnimalChangePaddockController;
import doyenm.zooshell.launch.play.Play;
import doyenm.zooshell.utils.Constants;
import doyenm.zooshell.validator.AnimalChangePaddockValidator;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 *
 * @author doyenm
 */
public class ChangePaddock extends AbstractCommand {

    private final AnimalChangePaddockController controller = new AnimalChangePaddockController();
    private final AnimalChangePaddockValidator validator = new AnimalChangePaddockValidator();

    public ChangePaddock(Play play) {
        super(play);
    }

    @Override
    public ReturnExec execute(String[] cmd) {
        try {
            AnimalChangePaddockContext context = new AnimalChangePaddockContext(this.getPlay().getZooModel(),
                    cmd[2], cmd[3]);
            context = Stream.of(context)
                    .filter(validator)
                    .map(controller)
                    .findFirst()
                    .get();
            getPlay().setZooModel(context.getZoo());
            setSuccess(true);
            return new ReturnExec("ANIMAL_CHANGE_PADDOCK_SUCCESS", TypeReturn.SUCCESS);
        } catch (java.util.NoSuchElementException ex) {
            ex.printStackTrace();
            return new ReturnExec("ERROR", TypeReturn.ERROR);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 4) {
            if (Arrays.asList(Constants.ANIMAL_PADDOCK).contains(cmd[0])) {
                if (Constants.UPDATE.equalsIgnoreCase(cmd[1])) {
                    return true;
                }
            }
        }
        return false;
    }

}
