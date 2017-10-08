package doyenm.zooshell.commandLine.commandImpl.zoo;

import doyenm.zooshell.utils.Constants;
import doyenm.zooshell.commandLine.general.Command;
import doyenm.zooshell.commandLine.general.DisplayingComplexEvents;
import doyenm.zooshell.commandLine.general.DisplayingSimpleEvents;
import doyenm.zooshell.commandLine.general.DisplayingEvents;
import doyenm.zooshell.commandLine.general.ReturnExec;
import doyenm.zooshell.commandLine.general.TypeReturn;
import doyenm.zooshell.context.EvaluationContext;
import doyenm.zooshell.controller.eventhandling.Event;
import doyenm.zooshell.controller.zoocontroller.EvaluationController;
import doyenm.zooshell.model.Zoo;
import java.util.ArrayList;
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
public class Evaluate implements Command {

    private final EvaluationController controller;
    
    private final List<DisplayingEvents> displayingEventsList = Arrays.asList(new DisplayingSimpleEvents(),
            new DisplayingComplexEvents()); 

    @Override
    public ReturnExec execute(String[] cmd, Zoo zoo) {
        EvaluationContext context = new EvaluationContext(zoo);
        Optional<EvaluationContext> optional = Stream.of(context)
                .map(controller)
                .findFirst();
        if (optional.isPresent()) {
            String events = formatEvents((EvaluationContext) optional.get());
            events += "The grade of the zoo is : " + ((EvaluationContext) optional.get()).getZoo().getGrade();
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
                .forEach((Event event) -> {
                    for(DisplayingEvents displayingEvents : displayingEventsList){
                        if(displayingEvents.canFormat(event)){
                            resultsList.add(displayingEvents.format(event));
                        }
                    }
                });
        for (String input : resultsList) {
            result += input + "\n";
        }
        return result;
    }

}
