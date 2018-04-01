package doyenm.zooshell.evaluation;

import doyenm.zooshell.evaluation.eventhandling.animal.AnimalEvent;
import doyenm.zooshell.model.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author doyenm
 */
@Getter
@Setter
public class AnimalEvaluationContext {

    public static final double BASE = 5.0;
    public static final double ZERO = 0.0;

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
    private double installationsWellBeing = 0.0;

    private WellBeing wellBeingObj = new WellBeing();
    
    private List<AnimalEvent> events = new ArrayList<>();

    public AnimalEvaluationContext(Zoo zoo, Animal animal) {
        this.zoo = zoo;
        this.animal = animal;
    }
    
    public double getMaxWellBeingWithoutKeeper(){
        return BASE
                + BASE
                + BASE * getUicnCoefficient()
                + BASE
                + BASE * getUicnCoefficient()
                + BASE * getUicnCoefficient()
                + BASE;
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

    public List<Animal> getAnimalsOfThePaddock(){
        return getZoo().getAnimals().values()
                .stream()
                .filter(other -> other.getPaddock().equals(getAnimal().getPaddock()))
                .collect(Collectors.toList());
    }

    public List<PaddockArrangement> getArrangements(){
        return getAnimal().getPaddock().getArrangements();
    }

    public List<PaddockArrangement> getSpecieArrangements(){
        return getAnimal().getSpecie().getArrangements().getArrangements();
    }

}
