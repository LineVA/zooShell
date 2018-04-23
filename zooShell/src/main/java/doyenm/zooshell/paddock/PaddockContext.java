package doyenm.zooshell.paddock;

import doyenm.zooshell.commandline.utils.Couple;
import doyenm.zooshell.errorhandling.BusinessError;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.Zoo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author doyenm
 */
@Getter
@Setter
@RequiredArgsConstructor
public class PaddockContext {

    private final Zoo zoo;
    private final String paddock;

    private List<BusinessError> errors = new ArrayList<>();

    private Paddock convertedPaddock;

    private List<Couple> couples = new ArrayList<>();

    public void addCouple(String key, String value) {
        couples.add(new Couple(key, value));
    }

    public void addCouple(String key, int value) {
        couples.add(new Couple(key, String.valueOf(value)));
    }
    
    public Collection<Animal> getAnimals(){
        return getZoo().getAnimals().values();
    }
}
