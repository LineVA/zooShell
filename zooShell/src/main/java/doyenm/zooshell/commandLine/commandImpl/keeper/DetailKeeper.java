package doyenm.zooshell.commandLine.commandImpl.keeper;

import doyenm.zooshell.commandLine.general.Command;
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
public class DetailKeeper implements Command {

    private final KeeperValidator validator;
    private final KeeperDetailsController controller;

    @Override
    public ReturnExec execute(String[] cmd, Zoo zoo) {
        KeeperContext context;
        if (cmd.length == 3) {
            context = new KeeperContext(zoo, cmd[1], Boolean.parseBoolean(cmd[2]));
        } else {
            context = new KeeperContext(zoo, cmd[1], false);
        }
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
        if (cmd.length == 2 | cmd.length == 3) {
            if (Arrays.asList(Constants.AK_OR_ANIMALKEEPER).contains(cmd[0])) {
                if (cmd.length == 3) {
                    return Constants.DETAILED.equals(cmd[2]);
                }
                return true;
            }
        }
        return false;
    }
}
