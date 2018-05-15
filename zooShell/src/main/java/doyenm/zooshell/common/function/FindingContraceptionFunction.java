package doyenm.zooshell.common.function;

import doyenm.zooshell.model.ContraceptionMethod;
import doyenm.zooshell.common.context.FindingContraceptionContext;

import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class FindingContraceptionFunction
        implements Function<FindingContraceptionContext, ContraceptionMethod> {

    ReplaceSpacesWithUnderscores replaceSpacesWithUnderscores = new ReplaceSpacesWithUnderscores();

    @Override
    public ContraceptionMethod apply(FindingContraceptionContext t) {
        FindingContraceptionContext context = t;
        try {
            int id = Integer.parseInt(context.getContraception());
            for (ContraceptionMethod method : ContraceptionMethod.values()) {
                if (id == method.getId()) {
                   return method;
                }
            }
        } catch (NumberFormatException ex) {
            String input = replaceSpacesWithUnderscores.replace(context.getContraception());
            for (ContraceptionMethod method : ContraceptionMethod.values()) {
                if (input.equalsIgnoreCase(method.name())) {
                    return method;
                }
            }
        }
        return null;
    }

}
