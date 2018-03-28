package doyenm.zooshell.keeper.details;

import doyenm.zooshell.commandline.general.Command;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.commandline.utils.FormattingInList;
import doyenm.zooshell.keeper.KeeperContext;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.utils.Constants;
import doyenm.zooshell.keeper.KeeperValidator;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

/**
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
        Optional<KeeperContext> optional = Stream.of(context)
                .filter(validator)
                .map(controller)
                .findFirst();
        if (optional.isPresent()) {
            FormattingInList formatting = new FormattingInList();
            return new ReturnExec(formatting.format(context.getCouples()), TypeReturn.SUCCESS);
        } else {
            return new ReturnExec("ERROR", TypeReturn.ERROR);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 2 || cmd.length == 3) {
            if (Arrays.asList(Constants.AK_OR_ANIMALKEEPER)
                    .stream()
                    .anyMatch(cmd[0]::equalsIgnoreCase)) {
                if (cmd.length == 3) {
                    return Arrays.asList(Constants.DETAILED)
                            .stream()
                            .anyMatch(cmd[2]::equalsIgnoreCase);
                }
                return true;
            }
        }
        return false;
    }
}
