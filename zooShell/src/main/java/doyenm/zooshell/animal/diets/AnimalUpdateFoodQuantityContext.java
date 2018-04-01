package doyenm.zooshell.animal.diets;

import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.Position;
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
public class AnimalUpdateFoodQuantityContext {

    private final Zoo zoo;
    private final String animal;
    private final String foodQuantity;

    private Animal convertedAnimal;
    private double convertedFoodQuantity;

    public void convert() {
        setConvertedFoodQuantity(Double.parseDouble(getFoodQuantity()));
    }
    
    public Position getEntry(){
        return getConvertedAnimal().getPaddock().getEntry();
    }
}
