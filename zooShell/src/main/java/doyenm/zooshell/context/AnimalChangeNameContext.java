package doyenm.zooshell.context;

import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.Zoo;
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
public class AnimalChangeNameContext {

    private final Zoo zoo;
    private final String currentName;
    private final String newName;

    private Animal convertedAnimal;

    public Map<String, Animal> getAnimals() {
        return getZoo().getAnimals();
    }
}
