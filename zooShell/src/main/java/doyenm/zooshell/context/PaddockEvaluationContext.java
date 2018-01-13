package doyenm.zooshell.context;

import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.controller.eventhandling.paddock.PaddockEvent;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.AnimalKeeper;
import doyenm.zooshell.model.Handyman;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author doyenm
 */
@Getter
@Setter
public class PaddockEvaluationContext {

    private final Zoo zoo;
    private Paddock paddock;
    List<PaddockEvent> paddockEvents;

    public PaddockEvaluationContext(Zoo zoo, Paddock paddock) {
        this.zoo = zoo;
        this.paddock = paddock;
        paddockEvents = new ArrayList<>();
    }

    public Map<String, Animal> getAnimals() {
        return getZoo().getAnimals();
    }

    public Map<String, AnimalKeeper> getKeepers() {
        return getZoo().getKeepers();
    }

    public Map<String, Handyman> getHandymen() {
        return getZoo().getHandymen();
    }
}
