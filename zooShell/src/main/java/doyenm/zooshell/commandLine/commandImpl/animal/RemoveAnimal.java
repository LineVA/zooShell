package doyenm.zooshell.commandLine.commandImpl.animal;

import doyenm.zooshell.commandLine.general.AbstractCommand;
import doyenm.zooshell.commandLine.general.ReturnExec;
import doyenm.zooshell.commandLine.general.TypeReturn;
import doyenm.zooshell.context.AnimalContext;
import doyenm.zooshell.controller.animalcontroller.AnimalRemoveController;
import doyenm.zooshell.launch.play.Play;
import doyenm.zooshell.utils.Constants;
import doyenm.zooshell.validator.AnimalValidator;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 *
 * @author doyenm
 */
public class RemoveAnimal extends AbstractCommand{

    private final AnimalRemoveController  controller = new AnimalRemoveController();
    private final AnimalValidator validator = new AnimalValidator();

    public RemoveAnimal(Play play) {
        super(play);
    }

    @Override
    public ReturnExec execute(String[] cmd) {
        try {
            AnimalContext context = new AnimalContext(this.getPlay().getZooModel(),
                    cmd[2]);
            context = Stream.of(context)
                    .filter(validator)
                    .map(controller)
                    .findFirst()
                    .get();
            getPlay().setZooModel(context.getZoo());
            setSuccess(true);
            return new ReturnExec("ANIMAL_REMOVE_SUCCESS", TypeReturn.SUCCESS);
        } catch (java.util.NoSuchElementException ex) {
            ex.printStackTrace();
            return new ReturnExec("ERROR", TypeReturn.ERROR);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 3) {
            if (Arrays.asList(Constants.ANIMAL).contains(cmd[0])) {
                if (Constants.REMOVE.equalsIgnoreCase(cmd[1])) {
                    return true;
                }
            }
        }
        return false;
    }

}
