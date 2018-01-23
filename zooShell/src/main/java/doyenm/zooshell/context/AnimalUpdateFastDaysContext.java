package doyenm.zooshell.context;

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
public class AnimalUpdateFastDaysContext {

    private final Zoo zoo;
    private final String animal;
    private final String fastDays;

    private Animal convertedAnimal;
    private int convertedFastDays;

    public void convert() {
        setConvertedFastDays(Integer.parseInt(getFastDays()));
    }
    
    public Position getEntry(){
        return getConvertedAnimal().getPaddock().getEntry();
    }
}