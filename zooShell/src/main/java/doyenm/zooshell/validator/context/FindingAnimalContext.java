package doyenm.zooshell.validator.context;

import doyenm.zooshell.model.Animal;
import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 *
 * @author doyenm
 */
@Getter
@RequiredArgsConstructor
public class FindingAnimalContext {
    private final Map<String, Animal> animals;
    private final String AnimalName;
    @Setter
    private Animal animal;
}
