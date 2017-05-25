package doyenm.zooshell.context;

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
}
