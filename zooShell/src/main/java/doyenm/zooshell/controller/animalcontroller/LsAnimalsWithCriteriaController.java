package doyenm.zooshell.controller.animalcontroller;

import doyenm.zooshell.context.LsWithCriteriaContext;
import doyenm.zooshell.model.Animal;
import doyenm.zooshell.model.Diet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import org.mvel2.MVEL;

/**
 *
 * @author doyenm
 */
public class LsAnimalsWithCriteriaController
        implements Function<LsWithCriteriaContext, LsWithCriteriaContext> {

    @Override
    public LsWithCriteriaContext apply(LsWithCriteriaContext t) {
        LsWithCriteriaContext context = t;
        List<Animal> animals = new ArrayList(context.getZoo().getAnimals().values());
        List<String> correctAnimals = new ArrayList<>();
        for (Animal animal : animals) {
            List<String> expression = context.getDietsExpression();
            for (Map.Entry<String, Diet> entry : context.getConvertedDiets().entrySet()) {
                if (animal.getDiets().contains(entry.getValue())) {
                    Collections.replaceAll(expression, entry.getKey(), "true");
                } else {
                    Collections.replaceAll(expression, entry.getKey(), "false");
                }
            }
            String expressionStr = "";
            for (String str : expression) {
                expressionStr += str + " ";
            }
            if ((Boolean) MVEL.eval(expressionStr)) {
                correctAnimals.add(animal.getName());
            }
        }
        context.getAnimalsList().addAll(correctAnimals);
        return context;
    }

}
