package doyenm.zooshell.context;

import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.Specie;
import doyenm.zooshell.model.Zoo;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author doyenm
 */
@Getter
@Setter
public class AnimalEvaluationContext {
    
    private final double base = 5.0;
    private final double zero = 0.0;

    private final Zoo zoo;
    private Animal animal;
    private boolean dead = false; 
    private List<Animal> children = new ArrayList<>();
    
    private double biomeWellBeing = 0;

    public AnimalEvaluationContext(Zoo zoo, Animal animal) {
        this.zoo = zoo;
        this.animal = animal;
    }

    public List<Animal> getAnimalsOfTheZoo(){
        return new ArrayList(getZoo().getAnimals().values());
    }
    
    public Specie getSpecie(){
        return getAnimal().getSpecie();
    }
    
    public Paddock getPaddock(){
        return getAnimal().getPaddock();
    }

}
