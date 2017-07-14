package doyenm.zooshell.controller.animalcontroller.evaluation;

import doyenm.zooshell.model.AnimalKeeper;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.TaskType;
import doyenm.zooshell.model.TimedOccupation;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *
 * @author doyenm
 */
public class KeeperUtils {

    public boolean isKeeperFeedingInGivenPaddock(AnimalKeeper keeper, Paddock paddock) {
        return !keeper.getOccupations()
                .stream()
                .filter((TimedOccupation t) -> TaskType.FEEDING == t.getTaskType())
                .filter((TimedOccupation t) -> paddock == t.getPaddock())
                .filter((TimedOccupation t) -> 0.0 != t.getTime())
                .collect(Collectors.toList()).isEmpty();
    }

    public List<TimedOccupation> listOfTimedOccupationInGivenPaddock(AnimalKeeper keeper, Paddock paddock) {
        return keeper.getOccupations()
                .stream()
                .filter((TimedOccupation t) -> paddock == t.getPaddock())
                .filter((TimedOccupation t) -> 0.0 != t.getTime())
                .collect(Collectors.toList());
    }

    public double timeSpentDoingTheTaskInThePaddock(AnimalKeeper keeper,
            TaskType task, Paddock paddock) {
        return keeper.getOccupations()
                .stream()
                .filter((TimedOccupation occupation) -> task == occupation.getTaskType())
                .filter((TimedOccupation occupation) -> paddock == occupation.getPaddock())
                .map(TimedOccupation::getTime)
                .findFirst()
                .get();
    }
}
