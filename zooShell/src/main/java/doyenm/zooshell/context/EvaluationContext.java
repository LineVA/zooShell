package doyenm.zooshell.context;

import doyenm.zooshell.controller.eventhandling.animal.AnimalEvent;
import doyenm.zooshell.controller.eventhandling.keeper.KeeperEvent;
import doyenm.zooshell.controller.eventhandling.paddock.PaddockEvent;
import doyenm.zooshell.controller.eventhandling.zoo.ZooEvent;
import doyenm.zooshell.model.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author doyenm
 */
@RequiredArgsConstructor
@Getter
@Setter
public class EvaluationContext {

    private final Zoo zoo;
    private double zooEvaluation = 0;
    private double paddocksEvaluation = 0;
    private double animalsEvaluation = 0;

    private Grade gradeObj = new Grade();

    private double totalEvaluation = 0;
    private List<Animal> evaluatedAnimalsList = new ArrayList<>();
    private List<AnimalKeeper> evaluatedKeepersList = new ArrayList<>();
    private List<Paddock> evaluatedPaddocksList = new ArrayList<>();
    private List<Handyman> evaluatedHandymenList = new ArrayList<>();

    private List<Animal> newBornsList = new ArrayList<>();
    private List<AnimalEvent> animalEvents = new ArrayList();
    private List<ZooEvent> zooEvents;
    private List<PaddockEvent> paddockEvents = new ArrayList<>();
    private List<KeeperEvent> keeperEvents = new ArrayList<>();

    public void updateAnimals() {
        Map<String, Animal> evaluatedAnimalsMap = convertAnimalsListToMap();
        List<String> removeAnimalsList = new ArrayList<>();
        this.getAnimals().keySet()
                .stream()
                .map((String t) -> {
                    if (evaluatedAnimalsMap.get(t.toUpperCase()) != null) {
                        getAnimals().replace(t, evaluatedAnimalsMap.get(t));
                    } else {
                        removeAnimalsList.add(t);
                    }
                    return t;
                })
                .findFirst();
        removeAnimals(removeAnimalsList);
        addAnimals(getNewBornsList());
    }

    public Map<String, Animal> getAnimals() {
        return getZoo().getAnimals();
    }

    public Map<String, AnimalKeeper> getKeepers() {
        return getZoo().getKeepers();
    }

    public Map<String, Paddock> getPaddocks() {
        return getZoo().getPaddocks();
    }

    public Map<String, Handyman> getHandymen() {
        return getZoo().getHandymen();
    }

    private Map<String, Animal> convertAnimalsListToMap() {
        Map<String, Animal> map = new HashMap<>();
        this.getEvaluatedAnimalsList()
                .stream()
                .map((Animal t) -> {
                    map.put(t.getName().toUpperCase(), t);
                    return t;
                })
                .collect(Collectors.toList());
        return map;
    }

    private void removeAnimals(List<String> animalsToRemoveList) {
        animalsToRemoveList
                .stream()
                .forEach(name ->
                    getAnimals().remove(name)
                );
    }

    private void addAnimals(List<Animal> animalsToAdd) {
        animalsToAdd
                .stream()
                .forEach(animal ->
                    getAnimals().put(animal.getName().toUpperCase(), animal)
                );
    }
}
