package doyenm.zooshell.animal;

import doyenm.zooshell.commandline.utils.Couple;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.Diet;
import doyenm.zooshell.model.FoodAttributes;
import doyenm.zooshell.model.Zoo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author doyenm
 */
@Getter
@Setter
@RequiredArgsConstructor
public class AnimalContext {

    private final Zoo zoo;
    private final String animal;
    private final boolean detailed;

    private Animal convertedAnimal;

    private List<Couple> couples = new ArrayList<>();

    public AnimalContext(Zoo zoo, String animal) {
        this.zoo = zoo;
        this.animal = animal;
        this.detailed = false;
    }
    
    

    public void addCouple(String key, String value) {
        couples.add(new Couple(key, value));
    }

    public void addCouple(String key, int value) {
        couples.add(new Couple(key, String.valueOf(value)));
    }

    public void addCouple(String key, double value) {
        couples.add(new Couple(key, String.valueOf(value)));
    }

    public String getSpecieName() {
        return this.getConvertedAnimal().getSpecie().getNames().getName();
    }

    public String getPaddockName() {
        return this.getConvertedAnimal().getPaddock().getName();
    }

    public String getSexName() {
        return this.getConvertedAnimal().getSex().toString();
    }

    public int getAge() {
        return this.getConvertedAnimal().getAge();
    }

    public double getWellBeing() {
        return this.getConvertedAnimal().getWellBeing();
    }

    public List<Diet> getDiet() {
        return this.getConvertedAnimal().getDiets();
    }

    public FoodAttributes getCurrentFoodAttributes() {
        return this.getConvertedAnimal().getCurrentFoodAttributes();
    }
}