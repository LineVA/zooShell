package doyenm.zooshell.context;

import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.AnimalKeeper;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.Zoo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
@Getter
@Setter
public class EvaluationContext {

    private final Zoo zoo;
    private int zooEvaluation;
    private int paddocksEvaluation;
    private int animalsEvaluation;
    private int totalEvaluation;
    private List<Animal> evaluatedAnimalsList = new ArrayList<>();
    private List<AnimalKeeper> evaluatedKeepersList = new ArrayList<>();
    private List<Paddock> evaluatedPaddocksList = new ArrayList<>();
    private List<Animal> newBornsList;
    
    public void updateZoo(){
    }

    public void updateKeepers(){
         Map<String, Animal> evaluatedAnimalsMap = convertAnimalsListToMap();
        Optional optional = this.getKeepers().values()
                .stream()
                .map((AnimalKeeper keeper) -> {
                    getKeepers().replace(keeper.getName(), keeper);
                    return keeper;
                })
                .findFirst();
    }
    
    public void updateAnimals() {
        Map<String, Animal> evaluatedAnimalsMap = convertAnimalsListToMap();
        List<String> removeAnimalsList = new ArrayList<>();
        Optional optional = this.getAnimals().keySet()
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
        addAnimals(newBornsList);
    }

    public Map<String, Animal> getAnimals() {
        return getZoo().getAnimals();
    }

    public Map<String, AnimalKeeper> getKeepers() {
        return getZoo().getKeepers();
    }

    private Map<String, Animal> convertAnimalsListToMap() {
        Map<String, Animal> map = new HashMap<>();
        this.getEvaluatedAnimalsList()
                .stream()
                .map(new Function<Animal, Animal>() {
                    @Override
                    public Animal apply(Animal t) {
                        map.put(t.getName().toUpperCase(), t);
                        return t;
                    }
                })
                .collect(Collectors.toList());
        return map;
    }

    private void removeAnimals(List<String> animalsToRemoveList) {
        for (String name : animalsToRemoveList) {
            getAnimals().remove(name);
        }
    }
    
    private void addAnimals(List<Animal> animalsToAdd) {
        for (Animal animal : animalsToAdd) {
            getAnimals().put(animal.getName().toUpperCase(), animal);
        }
    }
}
