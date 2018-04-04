package doyenm.zooshell;

import doyenm.zooshell.model.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 *
 * @author doyenm
 */
@Getter
@RequiredArgsConstructor
public class LsContext {

    private final Zoo zoo;

    public List<String> getPaddockNames() {
        return new ArrayList<>(
                getZoo().getPaddocks().values()
                .stream()
                .map(Paddock::getName)
                .collect(Collectors.toList())
        );
    }

    public List<String> getSpecieNames() {
        return new ArrayList<>(
                getZoo().getSpecies().values()
                .stream()
                .map(specie -> specie.getNames().getName())
                .collect(Collectors.toList())
        );
    }

    public List<String> getAnimalNames() {
        return new ArrayList<>(
                getZoo().getAnimals().values()
                .stream()
                .map(Animal::getName)
                .collect(Collectors.toList())
        );
    }

    public List<String> getKeeperNames() {
        return new ArrayList<>(
                getZoo().getKeepers().values()
                .stream()
                .map(AnimalKeeper::getName)
                .collect(Collectors.toList())
        );
    }
    
      public List<String> getHandymenNames() {
        return new ArrayList<>(
                getZoo().getHandymen().values()
                .stream()
                .map(Handyman::getName)
                .collect(Collectors.toList())
        );
    }
      
      public List<String> getPenalties(){
          return getZoo().getPenalties()
                  .stream()
                  .map(Penalty::toString)
                  .collect(Collectors.toList());
      }

    public List<String> getSexes() {
        List<String> list = new ArrayList<>();
        for (Sex sex : Sex.values()) {
            list.add(sex.getId() + " - " + sex.toString());
        }
        return list;
    }

    public List<String> getDiets() {
        List<String> list = new ArrayList<>();
        for (Diet diet : Diet.values()) {
            list.add(diet.getId() + " - " + diet.toString());
        }
        return list;
    }

    public List<String> getBiomes() {
        List<String> list = new ArrayList<>();
        for (Biome biome : Biome.values()) {
            list.add(biome.getId() + " - " + biome.toString());
        }
        return list;
    }

    public List<String> getPaddockTypes() {
        List<String> list = new ArrayList<>();
        for (PaddockType type : PaddockType.values()) {
            list.add(type.getId() + " - " + type.toString());
        }
        return list;
    }
    
    public List<String> getPaddockArrangements() {
        List<String> list = new ArrayList<>();
        for (PaddockArrangement arrangement : PaddockArrangement.values()) {
            list.add(arrangement.getId() + " - " + arrangement.toString());
        }
        return list;
    }

    public List<String> getContraceptionMethods() {
        List<String> list = new ArrayList<>();
        for (ContraceptionMethod method : ContraceptionMethod.values()) {
            list.add(method.getId() + " - " + method.toString());
        }
        return list;
    }

    public List<String> getTasks() {
        List<String> list = new ArrayList<>();
        for (TaskType task : TaskType.values()) {
            list.add(task.getId() + " - " + task.toString());
        }
        return list;
    }
}
