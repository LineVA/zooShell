package doyenm.zooshell.commandline.commandimpl;

import doyenm.zooshell.utils.Constants;
import doyenm.zooshell.commandline.general.Command;
import doyenm.zooshell.commandline.general.displayingevent.DisplayingEvents;
import doyenm.zooshell.commandline.general.ReturnExec;
import doyenm.zooshell.commandline.general.TypeReturn;
import doyenm.zooshell.context.EvaluationContext;
import doyenm.zooshell.controller.eventhandling.animal.AnimalEvent;
import doyenm.zooshell.controller.eventhandling.paddock.PaddockEvent;
import doyenm.zooshell.controller.eventhandling.zoo.ZooEvent;
import doyenm.zooshell.controller.EvaluationController;
import doyenm.zooshell.controller.eventhandling.keeper.KeeperEvent;
import doyenm.zooshell.model.Zoo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import lombok.RequiredArgsConstructor;

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
            String events = formatEvents((EvaluationContext) optional.get());
            events += "The grade of the zoo is : " + ((EvaluationContext) optional.get()).getZoo().getGrade();
            return new ReturnExec(events, TypeReturn.SUCCESS, context.getZoo());
        } else {
            return new ReturnExec("ERROR", TypeReturn.ERROR);
        }
    }

    @Override
    public boolean canExecute(String[] cmd) {
        return cmd.length == 1
                && Constants.EVALUATE.equals(cmd[0]);
    }

    private String formatEvents(EvaluationContext context) {
        String result = "";
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
        for (String input : resultsList) {
            result += input + "\n";
        }
        return result;
    }

}
