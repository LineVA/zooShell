package doyenm.zooshell.controller.animalcontroller;

import doyenm.zooshell.context.AnimalContext;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.Sex;
import doyenm.zooshell.utils.Utils;
import lombok.RequiredArgsConstructor;

import java.util.function.Function;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class AnimalDetailsController implements Function<AnimalContext, AnimalContext> {

    private final Utils utils;

    @Override
    public AnimalContext apply(AnimalContext t) {
        AnimalContext context = t;
        Animal animal = context.getConvertedAnimal();
        context.addCouple("Name", context.getAnimal());
        context.addCouple("Specie", context.getSpecieName());
        context.addCouple("Sex", displaySex(animal, context));
        int age = context.getAge();
        context.addCouple("Age", utils.getNumbersOfYearsFromAge(age) + " year(s), " + utils.getNumbersOfMonthsFromAge(age) + " month(s)");
        context.addCouple("Paddock", context.getPaddockName());
        context.addCouple("Diet", context.getDiet().toString());
        context.addCouple("Food attributes", context.getCurrentFoodAttributes().toString());
        context.addCouple("Contraception method", context.getConvertedAnimal().getContraceptionMethod().toString());
        return displayWellBeing(context);
    }

    private String displaySex(Animal animal, AnimalContext context) {
        if (animal.getAge() >= animal.getReproductionAttributes().getMaturityGivenSex(animal.getSex())) {
            return context.getSexName();
        } else {
            return Sex.UNKNOWN.toString();
        }
    }

    private AnimalContext displayWellBeing(AnimalContext t) {
        AnimalContext context = t;
        if (context.isDetailed()) {
            context.addCouple("WellBeing", context.getConvertedAnimal().getWellBeingObj().toString());
        } else {
            context.addCouple("WellBeing", context.getWellBeing());
        }
        return context;
    }

}
