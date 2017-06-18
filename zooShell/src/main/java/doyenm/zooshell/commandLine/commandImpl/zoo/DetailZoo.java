package doyenm.zooshell.commandLine.commandImpl.zoo;

import doyenm.zooshell.commandLine.general.CommandBis;
import doyenm.zooshell.commandLine.general.ReturnExec;
import doyenm.zooshell.commandLine.general.TypeReturn;
import doyenm.zooshell.commandLine.utils.FormattingInList;
import doyenm.zooshell.context.ZooDetailsContext;
import doyenm.zooshell.controller.zoocontroller.ZooDetailsController;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.utils.Constants;
import java.util.function.Function;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class DetailZoo implements CommandBis {

    private final ZooDetailsController controller;

    @Override
    public ReturnExec execute(String[] cmd, Zoo zoo) {
        ZooDetailsContext context = new ZooDetailsContext(zoo);
        return Stream.of(context)
                .map(controller)
                .map(new Function<ZooDetailsContext, ReturnExec>() {
                    @Override
                    public ReturnExec apply(ZooDetailsContext t) {
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
