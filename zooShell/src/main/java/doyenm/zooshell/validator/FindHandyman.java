package doyenm.zooshell.validator;

import doyenm.zooshell.model.Handyman;
import doyenm.zooshell.model.Zoo;
import doyenm.zooshell.validator.context.FindingHandymanContext;
import doyenm.zooshell.validator.function.FindingHandymanFunction;
import java.util.stream.Stream;

/**
 *
 * @author doyenm
 */
public class FindHandyman {

    FindingHandymanFunction findingHandyman = new FindingHandymanFunction();

    public Handyman find(Zoo zoo, String name) {
        FindingHandymanContext context = new FindingHandymanContext(zoo.getHandymen(), name.toUpperCase());
        return Stream.of(context)
                .map(findingHandyman)
                .findFirst()
                .get()
                .getHandyman();
    }
}
