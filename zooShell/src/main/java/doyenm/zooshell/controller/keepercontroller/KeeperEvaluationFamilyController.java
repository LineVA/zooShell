package doyenm.zooshell.controller.keepercontroller;

import doyenm.zooshell.context.KeeperEvaluationContext;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.TimedOccupation;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *
 * @author doyenm
 */
public class KeeperEvaluationFamilyController
        implements Function<KeeperEvaluationContext, KeeperEvaluationContext> {

    @Override
    public KeeperEvaluationContext apply(KeeperEvaluationContext t) {
        KeeperEvaluationContext context = t;
//        Map<Family, Double> familiesMap = context.getFamilyEvaluationMap();
//        Map<Paddock, Double> paddocksMap = this.generateTimedPaddockMap(context.getOccupations());
//        Map<Paddock, Integer> animalsPerPaddockMap = generateAnimalsPerPaddockMap(paddocksMap.entrySet().);
        return context;
    }

  

    private Map<Paddock, Double> generateTimedPaddockMap(List<TimedOccupation> occupations) {
        return occupations
                .stream()
                .collect(
                        Collectors.groupingBy(TimedOccupation::getPaddock,
                                Collectors.summingDouble(TimedOccupation::getTime)));
    }

}
