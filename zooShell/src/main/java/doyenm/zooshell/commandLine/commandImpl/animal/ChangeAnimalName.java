package doyenm.zooshell.commandLine.commandImpl.animal;

import doyenm.zooshell.commandLine.general.AbstractCommand;
import doyenm.zooshell.commandLine.general.ReturnExec;
import doyenm.zooshell.commandLine.general.TypeReturn;
import doyenm.zooshell.context.AnimalChangeNameContext;
import doyenm.zooshell.controller.animalcontroller.AnimalChangeNameController;
import doyenm.zooshell.launch.play.Play;
import doyenm.zooshell.utils.Constants;
import doyenm.zooshell.validator.AnimalChangeNameValidator;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 *
 * @author doyenm
 */
public class ChangeAnimalName  extends AbstractCommand {

    private final AnimalChangeNameController controller = new AnimalChangeNameController();
    private final AnimalChangeNameValidator validator = new AnimalChangeNameValidator();

    public ChangeAnimalName(Play play) {
        super(play);
    }

    @Override
    public ReturnExec execute(String[] cmd) {
        try {
            AnimalChangeNameContext context = new AnimalChangeNameContext(this.getPlay().getZooModel(),
                    cmd[2], cmd[3]);
            context = Stream.of(context)
                    .filter(validator)
                    .map(controller)
                    .findFirst()
                    .get();
            getPlay().setZooModel(context.getZoo());
            setSuccess(true);
            return new ReturnExec("ANIMAL_CHANGE_NAME_SUCCESS", TypeReturn.SUCCESS);
        } catch (java.util.NoSuchElementException ex) {
            ex.printStackTrace();
            return new ReturnExec("ERROR", TypeReturn.ERROR);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 4) {
            if (Arrays.asList(Constants.ANIMAL).contains(cmd[0])) {
                if (Constants.RENAME.equalsIgnoreCase(cmd[1])) {
                    return true;
                }
            }
        }
        return false;
    }

}
