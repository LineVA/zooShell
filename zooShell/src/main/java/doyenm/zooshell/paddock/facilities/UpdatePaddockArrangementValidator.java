package doyenm.zooshell.paddock.facilities;

import doyenm.zooshell.common.FindPaddock;
import doyenm.zooshell.common.context.FindingPaddockArrangementContext;
import doyenm.zooshell.common.function.FindingPaddockArrangementFunction;
import doyenm.zooshell.errorhandling.BusinessError;
import doyenm.zooshell.errorhandling.ErrorType;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.PaddockArrangement;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @author doyenm
 */
@RequiredArgsConstructor
public class UpdatePaddockArrangementValidator
        implements Function<UpdatePaddockArrangementContext, UpdatePaddockArrangementContext> {

    private final FindPaddock findPaddock;
    private final FindingPaddockArrangementFunction findingPaddockArrangementFunction;

    @Override
    public UpdatePaddockArrangementContext apply(UpdatePaddockArrangementContext context) {
        Paddock pad = findPaddock.find(context.getZoo(), context.getPaddock());
        if (pad == null) {
            context.getErrors().add(new BusinessError(ErrorType.UNKNOWN_PADDOCK));
        }
        context.setConvertedPaddock(pad);
        retrieveFacilities(context);
        return context;
    }

    private void retrieveFacilities(UpdatePaddockArrangementContext context) {
        FindingPaddockArrangementContext findingPaddockArrangementContext;
        PaddockArrangement convertedArrangement;
        context.setConvertedArrangements(new ArrayList<>());
        for (String str : context.getArrangements()) {
            findingPaddockArrangementContext
                    = new FindingPaddockArrangementContext(str);
            convertedArrangement = Stream.of(findingPaddockArrangementContext)
                    .map(findingPaddockArrangementFunction)
                    .filter(Objects::nonNull)
                    .findFirst()
                    .orElse(null);
            if (convertedArrangement == null) {
                context.getErrors().add(new BusinessError(ErrorType.UNKNOWN_PADDOCK_FACILITY));
            }
            context.getConvertedArrangements().add(convertedArrangement);
        }
    }
}
