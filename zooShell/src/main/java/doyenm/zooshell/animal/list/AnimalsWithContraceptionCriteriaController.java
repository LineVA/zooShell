package doyenm.zooshell.animal.list;

import doyenm.zooshell.model.ContraceptionMethod;
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
public class AnimalsWithContraceptionCriteriaController
        implements Function<AnimalsWithCriteriaContext, AnimalsWithCriteriaContext> {

    @Override
    public AnimalsWithCriteriaContext apply(AnimalsWithCriteriaContext t) {
        Optional<AnimalsWithCriteriaContext> optional = Arrays.asList(t)
                .stream()
                .map(t1 -> {
                    for (Map.Entry<String, ContraceptionMethod> entry : t1.getConvertedContraception().entrySet()) {
                        if (t1.getAnimal().getContraceptionMethod() == entry.getValue()) {
                            Collections.replaceAll(t1.getContraceptionExpressionList(), entry.getKey(), "true");
                        } else {
                            Collections.replaceAll(t1.getContraceptionExpressionList(), entry.getKey(), "false");
                        }
                    }
                    return t1;
                }).map(t1 -> {
                    t1.setContraceptionExpression(BuildingExpression.rebuildBooleanExpression(t1.getContraceptionExpressionList()));
                    return t1;
                })
                .findFirst();
        if(optional.isPresent()){
            return optional.get();
        } else {
            log.info("Fatal Error ; no value returned after examining contraception criterion on animal {}", t.getAnimal().getName());
            return t;
        }
    }

}
