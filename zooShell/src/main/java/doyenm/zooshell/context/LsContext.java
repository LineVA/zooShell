package doyenm.zooshell.context;

import doyenm.zooshell.model.Biome;
import doyenm.zooshell.model.ContraceptionMethod;
import doyenm.zooshell.model.Diet;
import doyenm.zooshell.model.PaddockType;
import doyenm.zooshell.model.Sex;
import doyenm.zooshell.model.Zoo;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@Getter
@RequiredArgsConstructor
public class LsContext {

    private final Zoo zoo;

    public List<String> getPaddockNames() {
        return new ArrayList<>(this.getZoo().getPaddocks().keySet());
    }

    public List<String> getSpecieNames() {
        return new ArrayList<>(this.getZoo().getSpecies().keySet());
    }

    public List<String> getAnimalNames() {
        return new ArrayList<>(this.getZoo().getAnimals().keySet());
    }

    public List<String> getSexes() {
        List<String> list = new ArrayList<>();
        for (Sex sex : Sex.values()) {
            list.add(sex.toString());
        }
        return list;
    }

    public List<String> getDiets() {
        List<String> list = new ArrayList<>();
        for (Diet diet : Diet.values()) {
            list.add(diet.toString());
        }
        return list;
    }

    public List<String> getBiomes() {
        List<String> list = new ArrayList<>();
        for (Biome biome : Biome.values()) {
            list.add(biome.toString());
        }
        return list;
    }

    public List<String> getPaddockTypes() {
        List<String> list = new ArrayList<>();
        for (PaddockType type : PaddockType.values()) {
            list.add(type.toString());
        }
        return list;
    }

    public List<String> getContraceptionMethods() {
        List<String> list = new ArrayList<>();
        for (ContraceptionMethod method : ContraceptionMethod.values()) {
            list.add(method.toString());
        }
        return list;
    }
}
