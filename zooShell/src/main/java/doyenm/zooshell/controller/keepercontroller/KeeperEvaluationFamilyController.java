package doyenm.zooshell.controller.keepercontroller;

import doyenm.zooshell.context.KeeperEvaluationContext;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.Family;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.TimedOccupation;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
        if (t.getKeeper().getTraining() != null) {
            return context;
        } else {
            return computeFamilyProgression(context);
        }
    }

    private KeeperEvaluationContext computeFamilyProgression(KeeperEvaluationContext context) {
        Map<Paddock, Double> paddocksMap = this.generateTimedPaddockMap(context.getOccupations());
        Map<Paddock, Set<Family>> familiesPerPaddockMap = generateFamiliesPerPaddockMap(context.getOccupations(), context.getAnimals());
        familiesPerPaddockMap.entrySet().stream().forEach((entry) -> {
            entry.getValue().stream().forEach((family) -> {
                if (context.getFamilyEvaluationMap().containsKey(family)) {
                    double currentValue = context.getFamilyEvaluationMap().get(family);
                    context.getKeeper().getFamilyEvaluations().replace(family, paddocksMap.get(entry.getKey()) + currentValue);
                } else {
                    context.getKeeper().getFamilyEvaluations().put(family, paddocksMap.get(entry.getKey()));
                }
            });
        });
        return context;
    }

    private Map<Paddock, Double> generateTimedPaddockMap(List<TimedOccupation> occupations) {
        return occupations
                .stream()
                .collect(
                        Collectors.groupingBy(TimedOccupation::getPaddock,
                                Collectors.summingDouble(TimedOccupation::getTime)));
    }

    private Map<Paddock, Set<Family>> generateFamiliesPerPaddockMap(List<TimedOccupation> occupations, List<Animal> animals) {
        Map<Paddock, Set<Family>> paddocksMap = new HashMap<>();
        for (TimedOccupation occupation : occupations) {
            paddocksMap.put(occupation.getPaddock(), new HashSet<>());
        }
        for (Animal animal : animals) {
            if (paddocksMap.containsKey(animal.getPaddock())) {
                Set<Family> families = paddocksMap.get(animal.getPaddock());
                families.add(animal.getSpecie().getFamily());
                paddocksMap.replace(animal.getPaddock(), families);
            }
        }
        return paddocksMap;
    }

}
