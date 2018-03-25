package doyenm.zooshell.controller.animalcontroller.criteria;

import java.util.List;

/**
 *
 * @author doyenm
 */
public class BuildingExpression {

    private BuildingExpression(){}

    public static String rebuildBooleanExpression(List<String> list) {
        StringBuilder builder = new StringBuilder();
        for (String str : list) {
            if (!str.equals(",")) {
                builder.append(str );
                builder.append(" ");
            }
        }
        return builder.toString();
    }

}
