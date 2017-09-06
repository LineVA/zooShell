package doyenm.zooshell.context;

import doyenm.zooshell.controller.eventhandling.Event;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.AnimalKeeper;
import doyenm.zooshell.model.Family;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.Specie;
import doyenm.zooshell.model.AnimalWellBeing;
import doyenm.zooshell.model.Zoo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
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

    private double biomeWellBeing = 0.0;
    private double dietsWellBeing = 0.0;
    private double foodQuantityWellBeing = 0.0;
    private double fastDaysWellBeing = 0.0;
    private double territorySizeWellBeing = 0.0;
    private double groupSizeWellBeing = 0.0;
    private double keeperInfluenceWellBeing = 0.0;
    private double taskInfluenceWellBeing = 0.0;

//    private double wellBeing = 0.0;
    
    private AnimalWellBeing wellBeingObj = new AnimalWellBeing();
    
    private List<Event> events = new ArrayList<>();

    public AnimalEvaluationContext(Zoo zoo, Animal animal) {
        this.zoo = zoo;
        this.animal = animal;
    }

    public int getNumberOfAnimalsOfTheSameSpecieAndInTheSamePaddock() {
        return getAnimalsOfTheZoo()
                .stream()
                .filter((Animal a) -> a.getSpecie().equals(getAnimal().getSpecie()))
                .filter((Animal a) -> a.getPaddock().equals(getAnimal().getPaddock()))
                .collect(Collectors.toList())
                .size();
    }

    public Collection<AnimalKeeper> getKeepers() {
        return getZoo().getKeepers().values();
    }

    public int getGestationDuration() {
        return getAnimal().getReproductionAttributes().getGestationDuration();
    }

    public int getMonthsPerTurn() {
        return getZoo().getMonthsPerEvaluation();
    }

    public int getCurrentGestationDuration() {
        return getAnimal().getMonthsOfGestation();
    }

    public void setCurrentGestationDuration(int i) {
        getAnimal().setMonthsOfGestation(i);
    }
    
    public double getUicnCoefficient(){
        return getAnimal().getSpecie().getUicn().getCoefficient();
    }
    
    public double getUicnStandardDeviation(){
        return getAnimal().getSpecie().getUicn().getStandardDeviation();
    }

    public List<Animal> getAnimalsOfTheZoo() {
        return new ArrayList(getZoo().getAnimals().values());
    }

    public Specie getSpecie() {
        return getAnimal().getSpecie();
    }

    public Paddock getPaddock() {
        return getAnimal().getPaddock();
    }
    
    public Family getFamily(){
        return getAnimal().getSpecie().getFamily();
    }

}
