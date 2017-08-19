package doyenm.zooshell.controller.zoocontroller;

import doyenm.zooshell.context.ZooContext;
import doyenm.zooshell.utils.Utils;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class ZooDetailsController implements Function<ZooContext, ZooContext> {

    private final Utils utils;
    
    @Override
    public ZooContext apply(ZooContext t) {
        ZooContext context = t;
        context.addCouple("Name", t.getZoo().getName());
        int age = context.getZoo().getAge();
        context.addCouple("Age", utils.getNumbersOfYearsFromAge(age) + " year(s), " + utils.getNumbersOfMonthsFromAge(age) + " month(s)");
        context.addCouple("Width", t.getZoo().getWidth());
        context.addCouple("Height", t.getZoo().getHeight());
        context.addCouple("Horizon", t.getZoo().getHorizon());
        context.addCouple("Months per turn", t.getZoo().getMonthsPerEvaluation());
        context.addCouple("Number of paddocks", t.getZoo().getPaddocks().size());
        context.addCouple("Number of animals", t.getZoo().getAnimals().size());
        context.addCouple("Number of animal keepers", t.getZoo().getKeepers().size());
        context.addCouple("Grade of the zoo", t.getZoo().getGrade());
        return context;
    }
}
