package doyenm.zooshell.controller.animalcontroller.evaluation.wellbeing;

import doyenm.zooshell.context.AnimalEvaluationContext;
import doyenm.zooshell.controller.animalcontroller.evaluation.KeeperUtils;
import doyenm.zooshell.model.AnimalKeeper;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.TimedOccupation;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class AnimalKeepersTimeInfluenceEvaluationController
        implements Function<AnimalEvaluationContext, AnimalEvaluationContext> {

    private final KeeperUtils keeperUtils;

    @Override
    public AnimalEvaluationContext apply(AnimalEvaluationContext t) {
        AnimalEvaluationContext context = t;
        context.setKeeperInfluenceWellBeing((computeWellBeing(context,
                context.getAnimal().getCharacterAttributes().getBravery())));
        return context;
    }

    private double computeWellBeing(AnimalEvaluationContext context, double trait) {
        Paddock paddock = context.getPaddock();
        double sum = 0.0;
        for (AnimalKeeper keeper : context.getKeepers()) {
            List<TimedOccupation> timedOccupations = keeperUtils.listOfTimedOccupationsInGivenPaddock(keeper, paddock);
            if (timedOccupations != null && !timedOccupations.isEmpty()) {
                sum += timedOccupations
                        .stream()
                        .filter((TimedOccupation k) -> keeper.getFamilyEvaluations().get(context.getFamily()) != null)
                        .collect(Collectors.summingDouble(TimedOccupation::getTime))
                        * keeper.getFamilyEvaluations().get(context.getFamily());
            }
        }
        if (trait >= 0.5) {
            return sum * context.getBase();
        }
        return -sum * context.getBase();
    }
}
