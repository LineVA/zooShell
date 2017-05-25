package doyenm.zooshell.controller.animalcontroller;

import doyenm.zooshell.context.AnimalDetailsContext;
import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class AnimalDetailsController implements Function<AnimalDetailsContext, AnimalDetailsContext> {

    @Override
    public AnimalDetailsContext apply(AnimalDetailsContext t) {
        AnimalDetailsContext context = t;
        context.addCouple("Name", context.getAnimal());
        context.addCouple("Specie", context.getSpecieName());
        context.addCouple("Sex", context.getSexName());
        context.addCouple("Age", context.getAge());
        context.addCouple("Paddock", context.getPaddockName());
        context.addCouple("Diet", context.getDiet().toString());
        context.addCouple("Food attributes", context.getCurrentFoodAttributes().toString());
        context.addCouple("Contraception method", context.getConvertedAnimal().getContraceptionMethod().toString());
        return context;
    }

}
