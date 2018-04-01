package doyenm.zooshell.animal.list;

import doyenm.zooshell.model.Specie;
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
public class AnimalsWithSpecieCriteriaController
        implements Function<AnimalsWithCriteriaContext, AnimalsWithCriteriaContext> {

    @Override
    public AnimalsWithCriteriaContext apply(AnimalsWithCriteriaContext t) {
        Optional<AnimalsWithCriteriaContext> optional = Arrays.asList(t)
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
                .findFirst();
        if(optional.isPresent()){
            return optional.get();
        } else {
            log.info("Fatal Error ; no value returned after examining xml criterion on animal {}", t.getAnimal().getName());
            return t;
        }
    }

}