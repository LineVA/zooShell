package doyenm.zooshell.animal.diets;

import doyenm.zooshell.animal.AnimalContext;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.Diet;

import java.util.Arrays;
import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class AnimalResetDietController implements Function<AnimalContext, AnimalContext> {

    @Override
    public AnimalContext apply(AnimalContext t) {
        Animal animal = t.getConvertedAnimal();
        animal.setDiets(Arrays.asList(Diet.NONE));
        t.getZoo().getAnimals().replace(t.getAnimal(), animal);
        return t;
    }

}
