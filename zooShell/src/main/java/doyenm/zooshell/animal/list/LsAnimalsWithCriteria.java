package doyenm.zooshell.animal.list;

import doyenm.zooshell.commandline.general.Command;
import doyenm.zooshell.commandline.general.ExtractingExpression;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.commandline.utils.FormattingInList;
import doyenm.zooshell.animal.LsWithCriteriaContext;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.utils.Constants;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
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
                retrievePaddocksExpression(cmd),
                retrieveSpeciesExpression(cmd)
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
        return cmd.length > 1
                && Arrays.asList(Constants.ANIMALS).contains(cmd[0]);
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

    private List<String> retrieveSpeciesExpression(String[] cmd) {
        return ExtractingExpression.extractExpression("xml", cmd);
    }
}
