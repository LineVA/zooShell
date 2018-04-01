package doyenm.zooshell.animal.list;

import doyenm.zooshell.model.Diet;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

/**
 *
 * @author doyenm
 */
@Slf4j
public class AnimalsWithDietCriteriaController
        implements Function<AnimalsWithCriteriaContext, AnimalsWithCriteriaContext> {

    @Override
    public AnimalsWithCriteriaContext apply(AnimalsWithCriteriaContext t) {
        Optional<AnimalsWithCriteriaContext> optional = Arrays.asList(t)
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
                .findFirst();
        if(optional.isPresent()){
            return optional.get();
        } else {
            log.info("Fatal Error ; no value returned after examining diet criterion on animal {}", t.getAnimal().getName());
            return t;
        }
    }

}
