package doyenm.zooshell.commandline.commandimpl.handyman;

import doyenm.zooshell.commandline.general.Command;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.commandline.utils.FormattingInList;
import doyenm.zooshell.context.HandymanContext;
import doyenm.zooshell.controller.handymancontroller.HandymanDetailsController;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.utils.Constants;
import doyenm.zooshell.validator.HandymanValidator;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author doyenm
 */
@RequiredArgsConstructor
public class DetailsHandyman implements Command {

    private final HandymanValidator validator;
    private final HandymanDetailsController controller;

    @Override
    public ReturnExec execute(String[] cmd, Zoo zoo) {
        HandymanContext context = generateContext(cmd, zoo);
        Optional<HandymanContext> optional = Stream.of(context)
                .filter(validator)
                .map(controller)
                .findFirst();
        if (optional.isPresent()) {
            FormattingInList formatting = new FormattingInList();
            return new ReturnExec(formatting.format(context.getCouples()), TypeReturn.SUCCESS, context.getZoo());
        } else {
            return new ReturnExec("ERROR", TypeReturn.ERROR, context.getZoo());
        }
    }

    private HandymanContext generateContext(String[] cmd, Zoo zoo) {
        return new HandymanContext(zoo, cmd[1]);
    }

    @Override
    public boolean canExecute(String[] cmd) {
        return cmd.length == 2
                && Arrays.asList(Constants.HANDYMAN_OR_HD).contains(cmd[0]);
    }
}
