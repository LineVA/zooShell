package doyenm.zooshell.commandLine.commandImpl.keeper;

import doyenm.zooshell.commandLine.general.CommandBis;
import doyenm.zooshell.commandLine.general.ReturnExec;
import doyenm.zooshell.commandLine.general.TypeReturn;
import doyenm.zooshell.commandLine.utils.FormattingInList;
import doyenm.zooshell.context.KeeperContext;
import doyenm.zooshell.controller.keepercontroller.KeeperDetailsController;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.utils.Constants;
import doyenm.zooshell.validator.KeeperValidator;
import java.util.Arrays;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class DetailKeeper implements CommandBis{

    private final KeeperValidator validator;
    private final KeeperDetailsController controller;


    @Override
    public ReturnExec execute(String[] cmd, Zoo zoo) {
        KeeperContext context = new KeeperContext(zoo, cmd[1]);
        context = Stream.of(context)
                .filter(validator)
                .map(controller)
                .findFirst() 
                .get();
        FormattingInList formatting = new FormattingInList();
        return new ReturnExec(formatting.format(context.getCouples()), TypeReturn.SUCCESS);
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 2) {
            if (Arrays.asList(Constants.AK_OR_ANIMALKEEPER).contains(cmd[0])) {
                return true;
            }
        }
        return false;
    }
}
