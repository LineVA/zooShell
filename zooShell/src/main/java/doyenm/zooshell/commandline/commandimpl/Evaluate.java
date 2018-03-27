package doyenm.zooshell.commandline.commandimpl;

import doyenm.zooshell.commandline.general.Command;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.commandline.general.displayingevent.DisplayingEvents;
import doyenm.zooshell.context.EvaluationContext;
import doyenm.zooshell.controller.EvaluationController;
import doyenm.zooshell.controller.eventhandling.animal.AnimalEvent;
import doyenm.zooshell.controller.eventhandling.keeper.KeeperEvent;
import doyenm.zooshell.controller.eventhandling.paddock.PaddockEvent;
import doyenm.zooshell.controller.eventhandling.zoo.ZooEvent;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.utils.Constants;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author doyenm
 */
@RequiredArgsConstructor
public class Evaluate implements Command {

    private final EvaluationController controller;

    private final List<DisplayingEvents> displayingEventsList;

    @Override
    public ReturnExec execute(String[] cmd, Zoo zoo) {
        EvaluationContext context = new EvaluationContext(zoo);
        Optional<EvaluationContext> optional = Stream.of(context)
                .map(controller)
                .findFirst();
        if (optional.isPresent()) {
            String events = formatEvents(optional.get());
            events += "The grade of the zoo is : " + (optional.get()).getZoo().getGrade();
            return new ReturnExec(events, TypeReturn.SUCCESS, context.getZoo());
        } else {
            return new ReturnExec("ERROR", TypeReturn.ERROR);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        return cmd.length == 1
                && Arrays.asList(Constants.EVALUATE)
                .stream()
                .anyMatch(cmd[0]::equalsIgnoreCase);
    }

    private String formatEvents(EvaluationContext context) {
        List<String> resultsList = new ArrayList<>();
        context.getAnimalEvents()
                .stream()
                .forEach((AnimalEvent event) -> {
                    for (DisplayingEvents displayingEvents : displayingEventsList) {
                        if (displayingEvents.canFormat(event)) {
                            resultsList.add(displayingEvents.format(event));
                        }
                    }
                });
        context.getPaddockEvents()
                .stream()
                .forEach((PaddockEvent event) -> {
                    for (DisplayingEvents displayingEvents : displayingEventsList) {
                        if (displayingEvents.canFormat(event)) {
                            resultsList.add(displayingEvents.format(event));
                        }
                    }
                });
        context.getKeeperEvents()
                .stream()
                .forEach((KeeperEvent event) -> {
                    for (DisplayingEvents displayingEvents : displayingEventsList) {
                        if (displayingEvents.canFormat(event)) {
                            resultsList.add(displayingEvents.format(event));
                        }
                    }
                });
        context.getZooEvents()
                .stream()
                .forEach((ZooEvent event) -> {
                    for (DisplayingEvents displayingEvents : displayingEventsList) {
                        if (displayingEvents.canFormat(event)) {
                            resultsList.add(displayingEvents.format(event));
                        }
                    }
                });
        StringBuilder builder = new StringBuilder();
        for (String input : resultsList) {
            builder.append(input);
            builder.append("\n");
        }
        return builder.toString();
    }

}
