package doyenm.zooshell.controller.animalcontroller.criteria;

import java.util.List;

/**
 *
 * @author doyenm
 */
public class BuildingExpression {

    public static String rebuildBooleanExpression(List<String> list) {
        String expressionStr = "";
        for (String str : list) {
            if (!str.equals(",")) {
                expressionStr += str + " ";
            }
        }
        return expressionStr;
    }

}