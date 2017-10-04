package doyenm.zooshell.controller.animalcontroller.criteria;

import doyenm.zooshell.context.AnimalsWithCriteriaContext;
import doyenm.zooshell.model.Diet;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class AnimalsWithDietCriteriaController
        implements Function<AnimalsWithCriteriaContext, AnimalsWithCriteriaContext> {

    @Override
    public AnimalsWithCriteriaContext apply(AnimalsWithCriteriaContext t) {
        return Arrays.asList(t)
                .stream()
                .map(t1 -> {
                    for (Map.Entry<String, Diet> entry : t1.getConvertedDiets().entrySet()) {
                        if (t1.getAnimal().getDiets().contains(entry.getValue())) {
                            Collections.replaceAll(t1.getDietExpressionList(), entry.getKey(), "true");
                        } else {
                            Collections.replaceAll(t1.getDietExpressionList(), entry.getKey(), "false");
                        }
                    }
                    return t1;
                }).map(t1 -> {
                    t1.setDietExpression(BuildingExpression.rebuildBooleanExpression(t1.getDietExpressionList()));
                    return t1;
                })
                .findFirst()
                .get();
    }

}
