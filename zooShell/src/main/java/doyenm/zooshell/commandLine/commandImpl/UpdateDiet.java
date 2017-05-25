package doyenm.zooshell.commandLine.commandImpl;

import doyenm.zooshell.commandLine.general.AbstractCommand;
import doyenm.zooshell.commandLine.general.ReturnExec;
import doyenm.zooshell.commandLine.general.TypeReturn;
import doyenm.zooshell.context.AnimalUpdateDietContext;
import doyenm.zooshell.controller.animalcontroller.AnimalUpdateDietController;
import doyenm.zooshell.launch.play.Play;
import doyenm.zooshell.utils.Constants;
import doyenm.zooshell.validator.AnimalUpdateDietValidator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 *
 * @author doyenm
 */
public class UpdateDiet extends AbstractCommand{

    private final AnimalUpdateDietValidator validator = new AnimalUpdateDietValidator();
    private final AnimalUpdateDietController controller = new AnimalUpdateDietController();

    public UpdateDiet(Play play) {
        super(play);
    }

    @Override
    public ReturnExec execute(String[] cmd) {
        try {
            AnimalUpdateDietContext context = new AnimalUpdateDietContext(this.getPlay().getZooModel(),
                    cmd[2], generateDietsList(cmd));
            context = Stream.of(context)
                    .filter(validator)
                    .map(controller)
                    .findFirst()
                    .get();
            getPlay().setZooModel(context.getZoo());
            setSuccess(true);
            return new ReturnExec("UPDATE_Diet_SUCCESS", TypeReturn.SUCCESS);
        } catch (java.util.NoSuchElementException ex) {
            return new ReturnExec("ERROR", TypeReturn.ERROR);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length >= 4) {
            if (Arrays.asList(Constants.ANIMAL_DIET).contains(cmd[0])) {
                if (Arrays.asList(Constants.UPDATE).contains(cmd[1])) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private List<String> generateDietsList(String[] cmd){
        List<String> list = new ArrayList<>();
        for (int i = 3 ; i<cmd.length ; i++){
            list.add(cmd[i]);
        }
        return list;
    }
}
