package doyenm.zooshell.commandLine.commandImpl;

import doyenm.zooshell.commandLine.general.CommandBis;
import doyenm.zooshell.commandLine.general.ReturnExec;
import doyenm.zooshell.commandLine.general.TypeReturn;
import doyenm.zooshell.commandLine.utils.FormattingInList;
import doyenm.zooshell.context.SpecieDetailsContext;
import doyenm.zooshell.controller.speciecontroller.SpecieDetailsController;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.utils.Constants;
import doyenm.zooshell.validator.SpecieDetailsValidator;
import java.util.Arrays;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class DetailSpecie implements CommandBis{

   private final SpecieDetailsValidator validator;
   private final SpecieDetailsController controller;

    @Override
    public ReturnExec execute(String[] cmd, Zoo zoo) {
        SpecieDetailsContext context = new SpecieDetailsContext(zoo, cmd[1]);
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
            if (Arrays.asList(Constants.SPEC_OR_SPECIE).contains(cmd[0])) {
                return true;
            }
        }
        return false;
    }
}
