package doyenm.zooshell.controller.animalcontroller.evaluation.wellbeing;

import com.google.inject.internal.util.ImmutableMap;
import doyenm.zooshell.context.AnimalEvaluationContext;
import doyenm.zooshell.controller.animalcontroller.evaluation.KeeperUtils;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.AnimalKeeper;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.TaskType;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class AnimalTasksInfluenceEvaluationController
        implements Function<AnimalEvaluationContext, AnimalEvaluationContext> {

    private final KeeperUtils keeperUtils;
    private final Double limitBetweenPositivAndNegativTime;

    @Override
    public AnimalEvaluationContext apply(AnimalEvaluationContext t) {
        AnimalEvaluationContext context = t;
        Map<TaskType, Double> charactersMap = generateCharactersMap(context.getAnimal());
        context.getWellBeingObj().setKeepersTaskWellBeing((computeWellBeingForTask(context, TaskType.CLEANING,
                charactersMap.get(TaskType.CLEANING))
                + computeWellBeingForTask(context, TaskType.ENRICHMENT,
                        charactersMap.get(TaskType.ENRICHMENT))
                + computeWellBeingForTask(context, TaskType.MEDICAL_TRAINING,
                        charactersMap.get(TaskType.MEDICAL_TRAINING))
                + computeWellBeingForTask(context, TaskType.FEEDING,
                        charactersMap.get(TaskType.FEEDING))
                + computeWellBeingForTask(context, TaskType.NURSING,
                        charactersMap.get(TaskType.NURSING)))
                / charactersMap.size());
        return context;
    }

    private Map<TaskType, Double> generateCharactersMap(Animal animal) {
        return ImmutableMap.<TaskType, Double>builder()
                .put(TaskType.CLEANING, animal.getCharacterAttributes().getMeticulousness())
                .put(TaskType.ENRICHMENT, animal.getCharacterAttributes().getCuriosity())
                .put(TaskType.MEDICAL_TRAINING, animal.getCharacterAttributes().getInteligence())
                .put(TaskType.FEEDING, animal.getCharacterAttributes().getGourmandise())
                .put(TaskType.NURSING, animal.getCharacterAttributes().getGourmandise())
                .build();
    }

    private double computeWellBeingForTask(AnimalEvaluationContext context, TaskType task, double trait) {
        Paddock paddock = context.getPaddock();
        double competence = 0.0;
        double sum = 0.0;
        for (AnimalKeeper keeper : context.getKeepers().stream().filter(k -> k.getTraining() == null).collect(Collectors.toList())) {
            if (keeper.getTaskEvaluations().containsKey(task)) {
                competence = keeper.getTaskEvaluations().get(task);
            } else {
                competence = 0.0;
            }
            sum += keeperUtils.timeSpentDoingTheTaskInThePaddock(keeper, task, paddock)
                    * competence;
        }
        if (trait >= limitBetweenPositivAndNegativTime) {
            return sum * context.getBase();
        }
        return -sum * context.getBase();
    }

}
