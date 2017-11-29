package doyenm.zooshell.controller.animalcontroller.criteria;

import doyenm.zooshell.context.AnimalsWithCriteriaContext;
import doyenm.zooshell.model.Sex;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class AnimalsWithSexCriteriaController
        implements Function<AnimalsWithCriteriaContext, AnimalsWithCriteriaContext> {

    @Override
    public AnimalsWithCriteriaContext apply(AnimalsWithCriteriaContext t) {
        return Arrays.asList(t)
                .stream()
                .map(t1 -> {
                    for (Map.Entry<String, Sex> entry : t1.getConvertedSexes().entrySet()) {
                        if (t1.getAnimal().getSex() == entry.getValue()) {
                            Collections.replaceAll(t1.getSexExpressionList(), entry.getKey(), "true");
                        } else {
                            Collections.replaceAll(t1.getSexExpressionList(), entry.getKey(), "false");
                        }
                    }
                    return t1;
                }).map(t1 -> {
                    t1.setSexExpression(BuildingExpression.rebuildBooleanExpression(t1.getSexExpressionList()));
                    return t1;
                })
                .findFirst()
                .get();
    }

}
