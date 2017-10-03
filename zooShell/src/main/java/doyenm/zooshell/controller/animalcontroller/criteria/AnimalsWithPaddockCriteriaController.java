package doyenm.zooshell.controller.animalcontroller.criteria;

import doyenm.zooshell.context.AnimalsWithCriteriaContext;
import doyenm.zooshell.model.Paddock;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class AnimalsWithPaddockCriteriaController
        implements Function<AnimalsWithCriteriaContext, AnimalsWithCriteriaContext> {

    @Override
    public AnimalsWithCriteriaContext apply(AnimalsWithCriteriaContext t) {
        return Arrays.asList(t)
                .stream()
                .map(t1 -> {
                    for (Map.Entry<String, Paddock> entry : t1.getConvertedPaddocks().entrySet()) {
                        if (t1.getAnimal().getPaddock().equals(entry.getValue())) {
                            Collections.replaceAll(t1.getPaddockExpressionList(), entry.getKey().toUpperCase(), "true");
                        } else {
                            Collections.replaceAll(t1.getPaddockExpressionList(), entry.getKey().toUpperCase(), "false");
                        }
                    }
                    return t1;
                }).map(t1 -> {
                    t1.setPaddockExpression(BuildingExpression.rebuildBooleanExpression(t1.getPaddockExpressionList()));
                    return t1;
                })
                .findFirst()
                .get();
    }

}
