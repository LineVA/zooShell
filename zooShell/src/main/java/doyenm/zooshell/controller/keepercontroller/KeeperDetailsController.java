package doyenm.zooshell.controller.keepercontroller;

import doyenm.zooshell.context.KeeperContext;
import doyenm.zooshell.model.AnimalKeeper;
import doyenm.zooshell.model.Family;
import doyenm.zooshell.model.TaskType;
import doyenm.zooshell.utils.Utils;
import java.util.ArrayList;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class KeeperDetailsController implements Function<KeeperContext, KeeperContext> {

    private final Utils utils;

    @Override
    public KeeperContext apply(KeeperContext t) {
        KeeperContext context = t;
        AnimalKeeper keeper = context.getConvertedKeeper();
        context.addCouple("Name", keeper.getName());
        int age = context.getConvertedKeeper().getAge();
        context.addCouple("Age", utils.getNumbersOfYearsFromAge(age) + " year(s), " + utils.getNumbersOfMonthsFromAge(age) + " month(s)");
        context.addCouple("Training", keeper.getTraining() == null ? "null" : keeper.getTraining().toString());
        context.addCouple("Occupations", keeper.getOccupations().toString());
        if (context.isDetailed()) {
            context.addCouplesList(new ArrayList(keeper.getTaskEvaluations().keySet()),
                    new ArrayList(keeper.getTaskEvaluations().values()));
            context.addCouplesList(new ArrayList(keeper.getFamilyEvaluations().keySet()),
                    new ArrayList(keeper.getFamilyEvaluations().values()));
        } else {
            context.addCouple("Average task evaluation", computeAverageTaskEvaluation(
                    keeper.getTaskEvaluations()));
            context.addCouple("Average family evaluation", computeAverageFamilyEvaluation(
                    keeper.getFamilyEvaluations()));
        }
        return context;
    }

    private double computeAverageTaskEvaluation(Map<TaskType, Double> map) {
        return map
                .values()
                .parallelStream()
                .collect(Collectors.summingDouble(d -> d));
    }

    private double computeAverageFamilyEvaluation(Map<Family, Double> map) {
        return map
                .values()
                .parallelStream()
                .collect(Collectors.summingDouble(d -> d));
    }

}
