package doyenm.zooshell.animal;

import doyenm.zooshell.common.FindAnimal;

import java.util.function.Predicate;

/**
 *
 * @author doyenm
 */
public class AnimalValidator implements Predicate<AnimalContext> {

    FindAnimal findAnimal = new FindAnimal();

    @Override
    public boolean test(AnimalContext t) {
        t.setConvertedAnimal(findAnimal.find(t.getZoo(), t.getAnimal()));
        if (t.getConvertedAnimal() != null) {
            return t.getConvertedAnimal().getPaddock().getEntry() != null;
        }
        return false;
    }
}
