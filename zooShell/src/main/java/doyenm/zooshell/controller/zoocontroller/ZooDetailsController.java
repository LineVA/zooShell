package doyenm.zooshell.controller.zoocontroller;

import doyenm.zooshell.context.ZooDetailsContext;
import doyenm.zooshell.utils.Utils;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class ZooDetailsController implements Function<ZooDetailsContext, ZooDetailsContext> {

    private final Utils utils;
    
    @Override
    public ZooDetailsContext apply(ZooDetailsContext t) {
        ZooDetailsContext context = t;
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
        return context;
    }
}
