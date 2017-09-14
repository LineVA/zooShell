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
        context.setDietsExpression(replaceOr(context.getDietsExpression()));
        context.setDietsExpression(replaceAnd(context.getDietsExpression()));
        return context;
    }

    private static List<String> replaceNot(List<String> expression) {
        Collections.replaceAll(expression, "not", "!");
        return expression;
    }

    private static List<String> replaceOr(List<String> expression) {
        Collections.replaceAll(expression, "or", "||");
        return expression;
    }

    private static List<String> replaceAnd(List<String> expression) {
        Collections.replaceAll(expression, "and", "&&");
        return expression;
    }
}
