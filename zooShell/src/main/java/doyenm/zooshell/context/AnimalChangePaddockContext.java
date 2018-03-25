package doyenm.zooshell.context;

import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.Zoo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Map;

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
    
    public Map<String, Paddock> getPaddocksMap(){
        return getZoo().getPaddocks();
    }
    
     public Map<String, Animal> getAnimalsMap(){
        return getZoo().getAnimals();
    }
}
