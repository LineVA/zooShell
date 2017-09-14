package doyenm.zooshell.validator;

import doyenm.zooshell.context.LsWithCriteriaContext;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author doyenm
 */
public class LsWithCriteriaParser {

    private final static List<String> excluded = Arrays.asList("and", "or", "not", "(", ")");

    public static LsWithCriteriaContext parse(LsWithCriteriaContext t) {
        LsWithCriteriaContext context = t;
        for (String str : context.getDietsExpression()) {
            if (!excluded.contains(str)) {
                context.getDiets().add(str);
            }
        }
        context.setDietsExpression(replaceNot(context.getDietsExpression()));
        return context;
    }

    private static List<String> replaceNot(List<String> expression) {
        Collections.replaceAll(expression, "not", "!");
        return expression;
    }
}
