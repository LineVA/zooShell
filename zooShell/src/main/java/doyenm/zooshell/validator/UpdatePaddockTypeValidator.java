package doyenm.zooshell.validator;

import doyenm.zooshell.context.UpdatePaddockTypeContext;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.validator.context.FindingPaddockTypeContext;
import doyenm.zooshell.validator.function.FindingPaddockTypeFunction;
import lombok.RequiredArgsConstructor;

import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class UpdatePaddockTypeValidator implements Predicate<UpdatePaddockTypeContext> {

    private final FindPaddock findPaddock;
    private final FindingPaddockTypeFunction findingPaddockTypeFunction;

    @Override
    public boolean test(UpdatePaddockTypeContext t) {
        UpdatePaddockTypeContext context = t;
        FindingPaddockTypeContext findingPaddockTypeContext = new FindingPaddockTypeContext(t.getType());
        Paddock pad = findPaddock.find(context.getZoo(), context.getPaddock());
        if (pad == null) {
            return false;
        }
        t.setConvertedType(Stream.of(findingPaddockTypeContext)
                .map(findingPaddockTypeFunction)
                .findFirst()
                .orElseGet(null)
                .getConvertedType());
       return t.getConvertedType()!= null;
    }
}
