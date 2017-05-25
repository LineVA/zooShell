package doyenm.zooshell.commandLine.commandImpl;

import doyenm.zooshell.commandLine.general.AbstractCommand;
import doyenm.zooshell.commandLine.general.ReturnExec;
import doyenm.zooshell.commandLine.general.TypeReturn;
import doyenm.zooshell.commandLine.utils.FormattingInList;
import doyenm.zooshell.context.AnimalDetailsContext;
import doyenm.zooshell.controller.animalcontroller.AnimalDetailsController;
import doyenm.zooshell.launch.play.Play;
import doyenm.zooshell.utils.Constants;
import doyenm.zooshell.validator.AnimalDetailsValidator;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 *
 * @author doyenm
 */
public class DetailAnimal extends AbstractCommand{

    AnimalDetailsController controller = new AnimalDetailsController();
    AnimalDetailsValidator validator = new AnimalDetailsValidator();

    public DetailAnimal(Play play) {
        super(play);
    }

    @Override
    public ReturnExec execute(String[] cmd) {
        super.setSuccess(true);
        AnimalDetailsContext context = new AnimalDetailsContext(getPlay().getZooModel(), cmd[1]);
        context = Stream.of(context)
                .filter(validator)
                .map(controller)
                .findFirst()
                .get();

        setSuccess(true);
        FormattingInList formatting = new FormattingInList();
        return new ReturnExec(formatting.format(context.getCouples()), TypeReturn.SUCCESS);
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 2) {
            if (Arrays.asList(Constants.ANIMAL).contains(cmd[0])) {
                return true;
            }
        }
        return false;
    }
}
