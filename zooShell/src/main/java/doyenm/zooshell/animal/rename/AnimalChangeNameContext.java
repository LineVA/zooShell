package doyenm.zooshell.animal.rename;

import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.Zoo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Set;

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

    public Set<String> getAnimals() {
        return getZoo().getAnimals().keySet();
    }
}
