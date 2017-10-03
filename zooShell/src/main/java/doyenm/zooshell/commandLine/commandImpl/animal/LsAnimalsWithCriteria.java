package doyenm.zooshell.commandLine.commandImpl.animal;

import doyenm.zooshell.commandLine.general.Command;
import doyenm.zooshell.commandLine.general.ExtractingExpression;
import doyenm.zooshell.commandLine.general.ReturnExec;
import doyenm.zooshell.commandLine.general.TypeReturn;
import doyenm.zooshell.commandLine.utils.FormattingInList;
import doyenm.zooshell.context.LsWithCriteriaContext;
import doyenm.zooshell.controller.animalcontroller.LsAnimalsWithCriteriaController;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.utils.Constants;
import doyenm.zooshell.validator.AnimalsWithCriteria;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class LsAnimalsWithCriteria implements Command {

    private final AnimalsWithCriteria validator;
    private final LsAnimalsWithCriteriaController controller;

    @Override
    public ReturnExec execute(String[] cmd, Zoo zoo) {
        LsWithCriteriaContext context = new LsWithCriteriaContext(zoo,
                retrieveDietsExpression(cmd),
                retrieveSexesExpression(cmd),
                retrievePaddocksExpression(cmd)
        );
        Optional<LsWithCriteriaContext> optionnal = Stream.of(context)
                .filter(validator)
                .map(controller)
                .findFirst();
        if (optionnal.isPresent()) {
            FormattingInList formatting = new FormattingInList();
            return new ReturnExec(formatting.formatList(context.getAnimalsList()), TypeReturn.SUCCESS, context.getZoo());
        } else {
            return new ReturnExec("ERROR", TypeReturn.ERROR, context.getZoo());
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length > 1) {
            if (Arrays.asList(Constants.ANIMALS).contains(cmd[0])) {
                return true;
            }
        }
        return false;
    }

    private List<String> retrieveDietsExpression(String[] cmd) {
        return ExtractingExpression.extractExpression("eating", cmd);
    }
    
     private List<String> retrieveSexesExpression(String[] cmd) {
        return ExtractingExpression.extractExpression("sex", cmd);
    }
     
      private List<String> retrievePaddocksExpression(String[] cmd) {
        return ExtractingExpression.extractExpression("paddock", cmd);
    }
}
