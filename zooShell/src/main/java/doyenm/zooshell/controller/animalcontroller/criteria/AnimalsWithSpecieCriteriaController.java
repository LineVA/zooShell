package doyenm.zooshell.controller.animalcontroller.criteria;

import doyenm.zooshell.context.AnimalsWithCriteriaContext;
import doyenm.zooshell.model.Specie;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class AnimalsWithSpecieCriteriaController
        implements Function<AnimalsWithCriteriaContext, AnimalsWithCriteriaContext> {

    @Override
    public AnimalsWithCriteriaContext apply(AnimalsWithCriteriaContext t) {
        return Arrays.asList(t)
                .stream()
                .map(t1 -> {
                    for (Map.Entry<String, Specie> entry : t1.getConvertedSpecies().entrySet()) {
                        if (t1.getAnimal().getSpecie().equals(entry.getValue())) {
                            Collections.replaceAll(t1.getSpecieExpressionList(), entry.getKey().toUpperCase(), "true");
                        } else {
                            Collections.replaceAll(t1.getSpecieExpressionList(), entry.getKey().toUpperCase(), "false");
                        }
                    }
                    return t1;
                }).map(t1 -> {
                    t1.setSpecieExpression(BuildingExpression.rebuildBooleanExpression(t1.getSpecieExpressionList()));
                    return t1;
                })
                .findFirst()
                .get();
    }

}
