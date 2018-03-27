package doyenm.zooshell.commandline.commandimpl.zoo;

import doyenm.zooshell.commandline.general.Command;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.commandline.utils.FormattingInList;
import doyenm.zooshell.context.ZooContext;
import doyenm.zooshell.controller.zoocontroller.ZooDetailsController;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.utils.Constants;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class DetailZoo implements Command {

    private final ZooDetailsController controller;

    @Override
    public ReturnExec execute(String[] cmd, Zoo zoo) {
        ZooContext context;
        if(cmd.length == 1){
            context = new ZooContext(zoo, false);
        } else {
            context = new ZooContext(zoo, true);
        }
        FormattingInList formatting = new FormattingInList();
        return new ReturnExec(formatting.format(context.getCouples()), TypeReturn.SUCCESS);
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 1) {
            if (Arrays.asList(Constants.ZOO)
                    .stream()
                    .anyMatch(cmd[0]::equalsIgnoreCase)) {
                return true;
            }
        } 
        return cmd.length == 2 
                && Arrays.asList(Constants.ZOO)
                .stream()
                .anyMatch(cmd[0]::equalsIgnoreCase)
                && Arrays.asList(Constants.DETAILED)
                .stream()
                .anyMatch(cmd[1]::equalsIgnoreCase);
    }
}
