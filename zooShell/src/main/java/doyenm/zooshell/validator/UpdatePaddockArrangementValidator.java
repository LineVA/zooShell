package doyenm.zooshell.validator;

import doyenm.zooshell.context.UpdatePaddockArrangementContext;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.validator.context.FindingPaddockArrangementContext;
import doyenm.zooshell.validator.function.FindingPaddockArrangementFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class UpdatePaddockArrangementValidator 
        implements Predicate<UpdatePaddockArrangementContext> {

    private final FindPaddock findPaddock;
    private final FindingPaddockArrangementFunction findingPaddockArrangementFunction;

    @Override
    public boolean test(UpdatePaddockArrangementContext t) {
        UpdatePaddockArrangementContext context = t;
        FindingPaddockArrangementContext findingPaddockArrangementContext 
                = new FindingPaddockArrangementContext(t.getArrangement());
        Paddock pad = findPaddock.find(context.getZoo(), context.getPaddock());
        if (pad == null) {
            return false;
        }
        t.setConvertedPaddock(pad);
        t.setConvertedArrangement(Stream.of(findingPaddockArrangementContext)
                .map(findingPaddockArrangementFunction)
                .findFirst()
                .get()
                .getConvertedArrangement());
       return t.getConvertedArrangement()!= null;
    }
}
