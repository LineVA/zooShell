package doyenm.zooshell.animal;

import doyenm.zooshell.common.function.ReplaceSpacesWithUnderscores;
import doyenm.zooshell.model.ContraceptionMethod;

import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class FindingContraceptionFunction
        implements Function<FindingContraceptionContext, FindingContraceptionContext> {

    ReplaceSpacesWithUnderscores replaceSpacesWithUnderscores = new ReplaceSpacesWithUnderscores();

    @Override
    public FindingContraceptionContext apply(FindingContraceptionContext t) {
        FindingContraceptionContext context = t;
        try {
            int id = Integer.parseInt(context.getContraception());
            for (ContraceptionMethod method : ContraceptionMethod.values()) {
                if (id == method.getId()) {
                    context.setConvertedContraception(method);
                }
            }
        } catch (NumberFormatException ex) {
            String input = replaceSpacesWithUnderscores.replace(context.getContraception());
            for (ContraceptionMethod method : ContraceptionMethod.values()) {
                if (input.equalsIgnoreCase(method.name())) {
                    context.setConvertedContraception(method);
                }
            }
        }
        return context;
    }

}
