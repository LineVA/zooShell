package doyenm.zooshell.commandLine.commandImpl;

import doyenm.zooshell.commandLine.general.AbstractCommand;
import doyenm.zooshell.commandLine.general.ReturnExec;
import doyenm.zooshell.commandLine.general.TypeReturn;
import doyenm.zooshell.commandLine.utils.FormattingInList;
import doyenm.zooshell.context.ZooDetailsContext;
import doyenm.zooshell.controller.zoocontroller.ZooDetailsController;
import doyenm.zooshell.launch.play.Play;
import doyenm.zooshell.utils.Constants;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 *
 * @author doyenm
 */
public class DetailZoo extends AbstractCommand {

    ZooDetailsController controller = new ZooDetailsController();

    public DetailZoo(Play play) {
        super(play);
    }

    @Override
    public ReturnExec execute(String[] cmd) {
        super.setSuccess(true);
        ZooDetailsContext context = new ZooDetailsContext(getPlay().getZooModel());
        return Stream.of(context)
                .map(controller)
                .map(new Function<ZooDetailsContext, ReturnExec>() {
                    @Override
                    public ReturnExec apply(ZooDetailsContext t) {
                        setSuccess(true);
                        FormattingInList formatting = new FormattingInList();
                        return new ReturnExec(formatting.format(context.getCouples()), TypeReturn.SUCCESS);
                    }

                })
                .findFirst()
                .get();
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 1) {
            if (Constants.ZOO.equalsIgnoreCase(cmd[0])) {
                return true;
            }
        }
        return false;
    }
}
