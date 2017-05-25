package doyenm.zooshell.commandLine.commandImpl;

import doyenm.zooshell.commandLine.general.AbstractCommand;
import doyenm.zooshell.commandLine.general.ReturnExec;
import doyenm.zooshell.commandLine.general.TypeReturn;
import doyenm.zooshell.context.AnimalContext;
import doyenm.zooshell.context.AnimalUpdateDietContext;
import doyenm.zooshell.controller.animalcontroller.AnimalResetDietController;
import doyenm.zooshell.launch.play.Play;
import doyenm.zooshell.utils.Constants;
import doyenm.zooshell.validator.AnimalValidator;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 *
 * @author doyenm
 */
public class ResetDiet extends AbstractCommand{

    private final AnimalValidator validator = new AnimalValidator();
    private final AnimalResetDietController controller = new AnimalResetDietController();

    public ResetDiet(Play play) {
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
            return new ReturnExec("RESET_DIET_SUCCESS", TypeReturn.SUCCESS);
        } catch (java.util.NoSuchElementException ex) {
            return new ReturnExec("ERROR", TypeReturn.ERROR);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 3) {
            if (Arrays.asList(Constants.ANIMAL_DIET).contains(cmd[0])) {
                if (Arrays.asList(Constants.RESET).contains(cmd[1])) {
                    return true;
                }
            }
        }
        return false;
    }
}
