package doyenm.zooshell.commandLine.commandImpl.paddock;

import doyenm.zooshell.commandLine.general.CommandBis;
import doyenm.zooshell.commandLine.general.ReturnExec;
import doyenm.zooshell.commandLine.general.TypeReturn;
import doyenm.zooshell.commandLine.utils.FormattingInList;
import doyenm.zooshell.context.PaddockContext;
import doyenm.zooshell.controller.paddockcontroller.PaddockDetailsController;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.utils.Constants;
import doyenm.zooshell.validator.PaddockValidator;
import java.util.Arrays;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class DetailPad implements CommandBis {

    private final PaddockValidator validator;
    private final PaddockDetailsController controller;

    @Override
    public ReturnExec execute(String[] cmd, Zoo zoo) {
        PaddockContext context = new PaddockContext(zoo, cmd[1]);
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
            if (Arrays.asList(Constants.PAD_OR_PADDOCK).contains(cmd[0])) {
                return true;
            }
        }
        return false;
    }

}
