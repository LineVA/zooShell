package doyenm.zooshell.controller.animalcontroller.evaluation.reproduction;

import doyenm.zooshell.context.AnimalEvaluationContext;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.ContraceptionMethod;
import doyenm.zooshell.model.Sex;
import java.util.Optional;
import java.util.function.Predicate;

/**
 *
 * @author doyenm
 */
public class MaleReproductionPredicate implements Predicate<AnimalEvaluationContext> {

    @Override
    public boolean test(AnimalEvaluationContext t) {
        // Animaux du même paddock
        // Animaux de la même espèce
        // est un male
        // sexuellement mature
        final Animal female = t.getAnimal();
        boolean a =female.getPaddock().equals(t.getAnimalsOfTheZoo().get(1).getPaddock());
        boolean b = female.getSpecie().equals(t.getAnimalsOfTheZoo().get(1).getSpecie());
        Optional optional = t.getAnimalsOfTheZoo()
                .stream()
                .filter((Animal t1) -> female.getPaddock().equals(t1.getPaddock()))
                .filter((Animal t1) -> female.getSpecie().equals(t1.getSpecie()))
                .filter((Animal t1) -> Sex.MALE == t1.getSex())
                .filter((Animal t2) -> ContraceptionMethod.NONE == t2.getContraceptionMethod())
                .filter((Animal t1) -> true)
                .filter((Animal t1) -> t1.getReproductionAttributes().getMaleMaturityAge() <= t1.getAge())
                .findFirst();
        return optional.isPresent();
    }

}
