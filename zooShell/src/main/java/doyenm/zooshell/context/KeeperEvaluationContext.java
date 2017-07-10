package doyenm.zooshell.context;

import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.AnimalKeeper;
import doyenm.zooshell.model.Family;
import doyenm.zooshell.model.TaskType;
import doyenm.zooshell.model.TimedOccupation;
import doyenm.zooshell.model.Zoo;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 *
 * @author doyenm
 */
@Getter
@Setter
@RequiredArgsConstructor
public class KeeperEvaluationContext {

    private final Zoo zoo;
    private final AnimalKeeper keeper;

    public List<TimedOccupation> getOccupations() {
        return getKeeper().getOccupations();
    }

    public List<Animal> getAnimals() {
        return new ArrayList(getZoo().getAnimals().values());
    }

    public Map<TaskType, Double> getTaskEvaluationMap() {
        return getKeeper().getTaskEvaluations();
    }

    public Map<Family, Double> getFamilyEvaluationMap() {
        return getKeeper().getFamilyEvaluations();
    }
}
