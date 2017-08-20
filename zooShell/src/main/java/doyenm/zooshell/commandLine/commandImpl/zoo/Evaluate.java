package doyenm.zooshell.commandLine.commandImpl.zoo;

import doyenm.zooshell.utils.Constants;
import doyenm.zooshell.commandLine.general.Command;
import doyenm.zooshell.commandLine.general.ReturnExec;
import doyenm.zooshell.commandLine.general.TypeReturn;
import doyenm.zooshell.context.EvaluationContext;
import doyenm.zooshell.controller.eventhandling.Event;
import doyenm.zooshell.controller.zoocontroller.EvaluationController;
import doyenm.zooshell.model.Zoo;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.assertj.core.util.Arrays;

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
            String events = formatEvents((EvaluationContext) optional.get());
            return new ReturnExec(events, TypeReturn.SUCCESS, context.getZoo());
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

    private String formatEvents(EvaluationContext context) {
        String result = "";
        List<String> resultsList = new ArrayList<>();
        context.getEvents()
                .stream()
                .forEach((Event event) ->{
                   MessageFormat msg = new MessageFormat(event.getEventType().getMessage());
                    resultsList.add(msg.format(new Object[]{event.getSubject().getName()}));
//                            Arrays.array(event.getSubject().getName())));
                });
        for(String input : resultsList){
            result += input + "\n";
        }
        return result;
    }

}
