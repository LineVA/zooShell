package doyenm.zooshell.controller.animalcontroller;

import doyenm.zooshell.context.AnimalDetailsContext;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.Sex;
import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class AnimalDetailsController implements Function<AnimalDetailsContext, AnimalDetailsContext> {

    @Override
    public AnimalDetailsContext apply(AnimalDetailsContext t) {
        AnimalDetailsContext context = t;
        Animal animal = context.getConvertedAnimal();
        context.addCouple("Name", context.getAnimal());
        context.addCouple("Specie", context.getSpecieName());
        if (animal.getAge() >= animal.getReproductionAttributes().getMaturityGivenSex(animal.getSex())) {
            context.addCouple("Sex", context.getSexName());
        } else {
            context.addCouple("Sex", Sex.UNKNOWN.toString());
        }
        context.addCouple("Age", context.getAge());
        context.addCouple("Paddock", context.getPaddockName());
        context.addCouple("Diet", context.getDiet().toString());
        context.addCouple("Food attributes", context.getCurrentFoodAttributes().toString());
        context.addCouple("Contraception method", context.getConvertedAnimal().getContraceptionMethod().toString());
        context.addCouple("Well-being", context.getWellBeing());
        return context;
    }

}
