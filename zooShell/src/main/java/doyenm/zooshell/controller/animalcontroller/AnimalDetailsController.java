package doyenm.zooshell.controller.animalcontroller;

import doyenm.zooshell.context.AnimalDetailsContext;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.Sex;
import doyenm.zooshell.utils.Utils;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class AnimalDetailsController implements Function<AnimalDetailsContext, AnimalDetailsContext> {
    
    private final Utils utils;

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
        int age = context.getAge();
        context.addCouple("Age", utils.getNumbersOfYearsFromAge(age) + " year(s), " + utils.getNumbersOfMonthsFromAge(age) +" month(s)");
        context.addCouple("Paddock", context.getPaddockName());
        context.addCouple("Diet", context.getDiet().toString());
        context.addCouple("Food attributes", context.getCurrentFoodAttributes().toString());
        context.addCouple("Contraception method", context.getConvertedAnimal().getContraceptionMethod().toString());
        context.addCouple("Well-being", context.getWellBeing());
        return context;
    }

}
