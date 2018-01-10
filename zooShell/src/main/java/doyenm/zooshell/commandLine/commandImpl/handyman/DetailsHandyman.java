package doyenm.zooshell.commandLine.commandImpl.handyman;

import doyenm.zooshell.commandLine.commandImpl.animal.*;
import doyenm.zooshell.commandLine.general.Command;
import doyenm.zooshell.commandLine.general.ReturnExec;
import doyenm.zooshell.commandLine.general.TypeReturn;
import doyenm.zooshell.commandLine.utils.FormattingInList;
import doyenm.zooshell.context.AnimalContext;
import doyenm.zooshell.context.HandymanContext;
import doyenm.zooshell.controller.animalcontroller.AnimalDetailsController;
import doyenm.zooshell.controller.handymancontroller.HandymanDetailsController;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.utils.Constants;
import doyenm.zooshell.validator.AnimalValidator;
import doyenm.zooshell.validator.HandymanValidator;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class DetailsHandyman implements Command {

    private final HandymanValidator validator;
    private final HandymanDetailsController controller;

    @Override
    public ReturnExec execute(String[] cmd, Zoo zoo) {
      HandymanContext context = generateContext(cmd, zoo);
        try {
            context = Stream.of(context)
                    .filter(validator)
                    .map(controller)
                    .findFirst()
                    .get();

            FormattingInList formatting = new FormattingInList();
            return new ReturnExec(formatting.format(context.getCouples()), TypeReturn.SUCCESS, context.getZoo());
        } catch (NoSuchElementException ex) {
            return new ReturnExec("ERROR", TypeReturn.ERROR, context.getZoo());
        }
    }
    
    private HandymanContext generateContext(String[] cmd, Zoo zoo){
           return new HandymanContext(zoo, cmd[1]);
    }

    @Override
    public boolean canExecute(String[] cmd) {
       return cmd.length == 2
                && Arrays.asList(Constants.HANDYMAN_OR_HD).contains(cmd[0]);
    }
}
