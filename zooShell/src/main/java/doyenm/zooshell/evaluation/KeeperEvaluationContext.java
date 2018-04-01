package doyenm.zooshell.evaluation;

import doyenm.zooshell.evaluation.eventhandling.keeper.KeeperEvent;
import doyenm.zooshell.model.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author doyenm
 */
@Getter
@Setter
public class KeeperEvaluationContext {

    private Zoo zoo;
    private AnimalKeeper keeper;
    private List<KeeperEvent> events = new ArrayList<>();

    public KeeperEvaluationContext(Zoo zoo, AnimalKeeper keeper) {
        this.zoo = zoo;
        this.keeper = keeper;
    }

    public int getKeeperAge() {
        return getKeeper().getAge();
    }

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

    public int getMonthsPerTurn() {
        return getZoo().getMonthsPerEvaluation();
    }
}
