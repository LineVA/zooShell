package doyenm.zooshell.animal.diets;

import doyenm.zooshell.animal.diets.AnimalUpdateDietContext;

import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class AnimalUpdateDietController
        implements Function<AnimalUpdateDietContext, AnimalUpdateDietContext> {

    @Override
    public AnimalUpdateDietContext apply(AnimalUpdateDietContext t) {
        t.getConvertedAnimal().setDiets(t.diffLists());
        t.getZoo().getAnimals().replace(t.getAnimal().toUpperCase(), t.getConvertedAnimal());
        return t;
    }

}
