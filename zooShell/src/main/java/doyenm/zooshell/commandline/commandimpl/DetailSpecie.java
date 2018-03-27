package doyenm.zooshell.commandline.commandimpl;

import doyenm.zooshell.commandline.general.Command;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.commandline.utils.FormattingInList;
import doyenm.zooshell.context.SpecieDetailsContext;
import doyenm.zooshell.controller.speciecontroller.SpecieDetailsController;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.utils.Constants;
import doyenm.zooshell.validator.SpecieDetailsValidator;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author doyenm
 */
@RequiredArgsConstructor
public class DetailSpecie implements Command {

    private final SpecieDetailsValidator validator;
    private final SpecieDetailsController controller;

    @Override
    public ReturnExec execute(String[] cmd, Zoo zoo) {
        SpecieDetailsContext context = new SpecieDetailsContext(zoo, cmd[1]);
        Optional<SpecieDetailsContext> optinal = Stream.of(context)
                .filter(validator)
                .map(controller)
                .findFirst();
        if(optinal.isPresent()) {
            FormattingInList formatting = new FormattingInList();
            return new ReturnExec(formatting.format(context.getCouples()), TypeReturn.SUCCESS);
        } else {
            return new ReturnExec("ERROR", TypeReturn.ERROR);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        return cmd.length == 2
                && Arrays.asList(Constants.SPEC_OR_SPECIE)
                .stream()
                .anyMatch(cmd[0]::equalsIgnoreCase);
    }
}
