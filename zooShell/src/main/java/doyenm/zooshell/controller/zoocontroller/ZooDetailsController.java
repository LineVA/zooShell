package doyenm.zooshell.controller.zoocontroller;

import doyenm.zooshell.context.ZooContext;
import doyenm.zooshell.utils.Utils;
import lombok.RequiredArgsConstructor;

import java.util.function.Function;

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
        context.addCouple("Number of penalties", t.getZoo().getPenalties().size());
        context.addCouple("Number of available trainings for keepers", t.getZoo().getAvailableKeeperTrainings().size());
        return displayGrade(context);
    }

    private ZooContext displayGrade(ZooContext t) {
        ZooContext context = t;
        if (context.isDetailed()) {
            context.addCouple("Grade", context.getZoo().getGradeObj().toString());
        } else {
            context.addCouple("Grade", context.getZoo().getGrade());
        }
        return context;
    }
}
