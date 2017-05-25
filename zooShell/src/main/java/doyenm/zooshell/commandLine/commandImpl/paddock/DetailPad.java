package doyenm.zooshell.commandLine.commandImpl.paddock;

import doyenm.zooshell.commandLine.general.AbstractCommand;
import doyenm.zooshell.commandLine.general.ReturnExec;
import doyenm.zooshell.commandLine.general.TypeReturn;
import doyenm.zooshell.commandLine.utils.FormattingInList;
import doyenm.zooshell.context.PaddockDetailsContext;
import doyenm.zooshell.controller.paddockcontroller.PaddockDetailsController;
import doyenm.zooshell.launch.play.Play;
import doyenm.zooshell.utils.Constants;
import doyenm.zooshell.validator.PaddockDetailsValidator;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 *
 * @author doyenm
 */
public class DetailPad extends AbstractCommand {

    PaddockDetailsController controller = new PaddockDetailsController();
    PaddockDetailsValidator validator = new PaddockDetailsValidator();

    public DetailPad(Play play) {
        super(play);
    }

    @Override
    public ReturnExec execute(String[] cmd) {
        super.setSuccess(true);
        PaddockDetailsContext context = new PaddockDetailsContext(getPlay().getZooModel(), cmd[1]);
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
            if (Arrays.asList(Constants.PAD_OR_PADDOCK).contains(cmd[0])) {
                return true;
            }
        }
        return false;
    }

}
