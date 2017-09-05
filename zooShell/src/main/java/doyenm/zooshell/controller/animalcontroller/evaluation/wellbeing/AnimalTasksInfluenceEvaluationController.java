package doyenm.zooshell.controller.animalcontroller.evaluation.wellbeing;

import doyenm.zooshell.context.AnimalEvaluationContext;
import doyenm.zooshell.controller.animalcontroller.evaluation.KeeperUtils;
import doyenm.zooshell.model.AnimalKeeper;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.TaskType;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class AnimalTasksInfluenceEvaluationController
        implements Function<AnimalEvaluationContext, AnimalEvaluationContext> {

    private final KeeperUtils keeperUtils;

    @Override
    public AnimalEvaluationContext apply(AnimalEvaluationContext t) {
        AnimalEvaluationContext context = t;
        context.getWellBeingObj().setKeepersTaskWellBeing((computeWellBeingForTask(context, TaskType.CLEANING,
                context.getAnimal().getCharacterAttributes().getMeticulousness())
                + computeWellBeingForTask(context, TaskType.ENRICHMENT,
                        context.getAnimal().getCharacterAttributes().getCuriosity())
                + computeWellBeingForTask(context, TaskType.MEDICAL_TRAINING,
                        context.getAnimal().getCharacterAttributes().getInteligence())
                + computeWellBeingForTask(context,
                        TaskType.FEEDING, context.getAnimal().getCharacterAttributes().getGourmandise()))
                + computeWellBeingForTask(context,
                        TaskType.NURSING, context.getAnimal().getCharacterAttributes().getGourmandise())
                / 5.0
        );
        return context;
    }

    private double computeWellBeingForTask(AnimalEvaluationContext context, TaskType task, double trait) {
        Paddock paddock = context.getPaddock();
        double competence = 0.0;
        double sum = 0.0;
        for (AnimalKeeper keeper : context.getKeepers()) {
            if (keeper.getTaskEvaluations().containsKey(task)) {
                competence = keeper.getTaskEvaluations().get(task);
            } else {
                competence = 0.0;
            }
            sum += keeperUtils.timeSpentDoingTheTaskInThePaddock(keeper, task, paddock)
                    * competence;
        }
        if (trait >= 0.5) {
            return sum * context.getBase();
        }
        return -sum * context.getBase();
    }

}
