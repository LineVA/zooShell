package doyenm.zooshell.animal.move;

import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.Zoo;
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
public class AnimalChangePaddockContext {
    private final Zoo zoo;
    private final String animal;
    private final String paddock;
    
    private Animal convertedAnimal;
    private Paddock convertedPaddock;
    
}
