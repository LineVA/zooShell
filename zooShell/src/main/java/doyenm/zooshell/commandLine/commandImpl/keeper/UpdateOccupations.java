package doyenm.zooshell.commandLine.commandImpl.keeper;

import doyenm.zooshell.commandLine.general.AbstractCommand;
import doyenm.zooshell.commandLine.general.ReturnExec;
import doyenm.zooshell.commandLine.general.TypeReturn;
import doyenm.zooshell.context.KeeperUpdateOccupationsContext;
import doyenm.zooshell.controller.keepercontroller.KeeperUpdateOccupationsController;
import doyenm.zooshell.launch.play.Play;
import doyenm.zooshell.utils.Constants;
import doyenm.zooshell.validator.KeeperUpdateOccupationsValidator;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 *
 * @author doyenm
 */
public class UpdateOccupations extends AbstractCommand{

    private final KeeperUpdateOccupationsController controller = new KeeperUpdateOccupationsController();
    private final KeeperUpdateOccupationsValidator validator = new KeeperUpdateOccupationsValidator();

    public UpdateOccupations(Play play) {
        super(play);
    }

    @Override
    public ReturnExec execute(String[] cmd) {
        try {
            KeeperUpdateOccupationsContext context = new KeeperUpdateOccupationsContext(this.getPlay().getZooModel(),
                    cmd[2], cmd[3], cmd[4], cmd[5]);
            context = Stream.of(context)
                    .filter(validator)
                    .map(controller)
                    .findFirst()
                    .get();
            getPlay().setZooModel(context.getZoo());
            setSuccess(true);
            return new ReturnExec("KEEPER_UPATE_OCCUPATIONS_SUCCESS", TypeReturn.SUCCESS);
        } catch (java.util.NoSuchElementException ex) {
            ex.printStackTrace();
            return new ReturnExec("ERROR", TypeReturn.ERROR);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 6) {
            if (Arrays.asList(Constants.AK_OR_ANIMALKEEPER_OCCUPATIONS).contains(cmd[0])) {
                if (Constants.UPDATE.equalsIgnoreCase(cmd[1])) {
                    return true;
                }
            }
        }
        return false;
    }

}