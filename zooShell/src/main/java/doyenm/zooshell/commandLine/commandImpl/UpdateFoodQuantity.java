package doyenm.zooshell.commandLine.commandImpl;

import doyenm.zooshell.commandLine.general.AbstractCommand;
import doyenm.zooshell.commandLine.general.ReturnExec;
import doyenm.zooshell.commandLine.general.TypeReturn;
import doyenm.zooshell.context.AnimalUpdateFoodQuantityContext;
import doyenm.zooshell.controller.animalcontroller.AnimalUpdateFoodQuantityController;
import doyenm.zooshell.launch.play.Play;
import doyenm.zooshell.utils.Constants;
import doyenm.zooshell.validator.AnimalUpdateFoodQuantityValidator;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 *
 * @author doyenm
 */
public class UpdateFoodQuantity extends AbstractCommand{

    private final AnimalUpdateFoodQuantityValidator validator = new AnimalUpdateFoodQuantityValidator();
    private final AnimalUpdateFoodQuantityController controller = new AnimalUpdateFoodQuantityController();

    public UpdateFoodQuantity(Play play) {
        super(play);
    }

    @Override
    public ReturnExec execute(String[] cmd) {
        try {
            AnimalUpdateFoodQuantityContext context = new AnimalUpdateFoodQuantityContext(this.getPlay().getZooModel(),
                    cmd[2], cmd[3]);
            context = Stream.of(context)
                    .filter(validator)
                    .map(controller)
                    .findFirst()
                    .get();
            getPlay().setZooModel(context.getZoo());
            setSuccess(true);
            return new ReturnExec("UPDATE_FOOD_QUANTITY_SUCCESS", TypeReturn.SUCCESS);
        } catch (java.util.NoSuchElementException ex) {
            return new ReturnExec("ERROR", TypeReturn.ERROR);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 4) {
            if (Arrays.asList(Constants.ANIMAL_FOOD_QUANTITY).contains(cmd[0])) {
                if (Arrays.asList(Constants.UPDATE).contains(cmd[1])) {
                    return true;
                }
            }
        }
        return false;
    }
}
