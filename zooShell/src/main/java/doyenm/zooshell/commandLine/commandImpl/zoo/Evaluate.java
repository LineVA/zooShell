package doyenm.zooshell.commandLine.commandImpl.zoo;

import doyenm.zooshell.utils.Constants;
import doyenm.zooshell.commandLine.general.Command;
import doyenm.zooshell.commandLine.general.ReturnExec;
import doyenm.zooshell.commandLine.general.TypeReturn;
import doyenm.zooshell.context.EvaluationContext;
import doyenm.zooshell.context.PaddockContext;
import doyenm.zooshell.controller.zoocontroller.EvaluationController;
import doyenm.zooshell.model.Zoo;
import java.util.Optional;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class Evaluate implements Command {
    
    private final EvaluationController controller;

    @Override
    public ReturnExec execute(String[] cmd, Zoo zoo) {
        EvaluationContext context = new EvaluationContext(zoo);
        Optional<EvaluationContext> optional = Stream.of(context)
                .map(controller)
                .findFirst();
        if (optional.isPresent()) {
            return new ReturnExec("OK", TypeReturn.SUCCESS, context.getZoo());
        } else {
            return new ReturnExec("ERROR", TypeReturn.ERROR);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        if (cmd.length == 1) {
            return Constants.EVALUATE.equals(cmd[0]);
        }
        return false;
    }

}
