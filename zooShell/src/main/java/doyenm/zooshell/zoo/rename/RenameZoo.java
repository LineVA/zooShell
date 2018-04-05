package doyenm.zooshell.zoo.rename;

import doyenm.zooshell.commandline.general.Command;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.commandline.utils.FormattingInList;
import doyenm.zooshell.errorhandling.BusinessError;
import doyenm.zooshell.errorhandling.ErrorType;
import doyenm.zooshell.zoo.ZooContext;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.utils.Constants;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author doyenm
 */
@RequiredArgsConstructor
public class RenameZoo implements Command {

    private final RenameZooValidator validator;
    private final RenameZooController controller;

    @Override
    public ReturnExec execute(String[] cmd, Zoo zoo) {
        ZooContext context;
        context = new ZooContext(zoo, cmd[2]);
        Optional<ZooContext> optional = Stream.of(context)
                .map(validator)
                .filter(i -> i.getErrors().isEmpty())
                .map(controller)
                .findFirst();
        if (optional.isPresent()) {
            return new ReturnExec("ZOO_CHANGE_NAME_SUCCESS", TypeReturn.SUCCESS, context.getZoo());
        } else {
            List<String> errors = context.getErrors()
                    .stream()
                    .map(BusinessError::getType)
                    .map(ErrorType::toString)
                    .collect(Collectors.toList());
            return new ReturnExec(FormattingInList.formatList(errors), TypeReturn.ERROR);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        return cmd.length == 3
                && Arrays.asList(Constants.ZOO)
                .stream()
                .anyMatch(cmd[0]::equalsIgnoreCase)
                && Arrays.asList(Constants.RENAME)
                .stream()
                .anyMatch(cmd[1]::equalsIgnoreCase);
    }
}
