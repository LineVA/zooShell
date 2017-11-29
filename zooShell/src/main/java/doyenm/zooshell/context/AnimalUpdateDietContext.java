package doyenm.zooshell.context;

import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.Diet;
import doyenm.zooshell.model.Position;
import doyenm.zooshell.model.Zoo;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
public class AnimalUpdateDietContext {

    private final Zoo zoo;
    private final String animal;
    private final List<String> diets;

    private Animal convertedAnimal;
    private List<Diet> convertedDiets = new ArrayList<>();

    public void build() {
        this.getConvertedAnimal().setDiets(diffLists());
        this.getZoo().getAnimals().replace(this.getAnimal(), this.getConvertedAnimal());
    }
    
    public Position getEntry(){
        return getConvertedAnimal().getPaddock().getEntry();
    }

    private List<Diet> diffLists() {
        Set<Diet> set = new HashSet<>();
        set.addAll(this.getConvertedDiets());
        set.addAll(this.getConvertedAnimal().getDiets());
        set.remove(Diet.NONE);
        return new ArrayList<>(set);
    }
}
