package doyenm.zooshell.controller.animalcontroller.evaluation.reproduction;

import doyenm.zooshell.context.AnimalEvaluationContext;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.ContraceptionMethod;
import doyenm.zooshell.model.Sex;
import doyenm.zooshell.utils.UniformStatistics;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 *
 * @author doyenm
 */
public class FemaleReproductionPredicate implements Predicate<AnimalEvaluationContext> {

    UniformStatistics uniformStatistics = new UniformStatistics();

    @Override
    public boolean test(AnimalEvaluationContext t) {
        Optional optional = Stream.of(t.getAnimal())
                .filter((Animal t2) -> 0 == t2.getMonthsOfGestation())
                .filter((Animal t2) -> Sex.FEMALE == t2.getSex())
                .filter((Animal t2) -> ContraceptionMethod.NONE == t2.getContraceptionMethod())
                .filter((Animal t2) -> t2.getReproductionAttributes().getFemaleMaturityAge() <= t2.getAge())
                // Well-being
                .filter((Animal t2) -> true)
                .filter((Animal t2) -> {
                        boolean result = uniformStatistics.uniform()
                        <= t2.getReproductionAttributes().getFrequency() * t.getZoo().getMonthsPerEvaluation() / 12;
                        return result;
                })
                .findFirst();
        return optional.isPresent();
    }

}
