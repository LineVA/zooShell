package doyenm.zooshell.validator.criteria;

import doyenm.zooshell.context.LsWithCriteriaContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author doyenm
 */
public class LsWithCriteriaParser {

    public static List<String> parse(List<String> init, List<String> excluded) {
        List<String> result = new ArrayList<>();
        for (String str : init) {
            if (!excluded.contains(str)) {
                result.add(str);
            }
        }
        return result;
    }
    
    public static List<String> replaceGrammaticalExpression(List<String> init){
        replaceAnd(init);
        replaceNot(init);
        replaceOr(init);
        return init;
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
