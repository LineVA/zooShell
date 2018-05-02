package doyenm.zooshell.paddock.types;

import doyenm.zooshell.common.FindPaddock;
import doyenm.zooshell.common.context.FindingPaddockTypeContext;
import doyenm.zooshell.common.function.FindingPaddockTypeFunction;
import doyenm.zooshell.errorhandling.BusinessError;
import doyenm.zooshell.errorhandling.ErrorType;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.PaddockType;
import lombok.RequiredArgsConstructor;

import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class UpdatePaddockTypeValidator
        implements Function<UpdatePaddockTypeContext, UpdatePaddockTypeContext> {

    private final FindPaddock findPaddock;
    private final FindingPaddockTypeFunction findingPaddockTypeFunction;

    @Override
    public UpdatePaddockTypeContext apply(UpdatePaddockTypeContext t) {
        UpdatePaddockTypeContext context = t;
        FindingPaddockTypeContext findingPaddockTypeContext = new FindingPaddockTypeContext(t.getType());
        Paddock pad = findPaddock.find(context.getZoo(), context.getPaddock());
        if (pad == null) {
           context.getErrors().add(new BusinessError(ErrorType.UNKNOWN_PADDOCK));
        }
        t.setConvertedPaddock(pad);
        PaddockType type = Stream.of(findingPaddockTypeContext)
                .map(findingPaddockTypeFunction)
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
        if(type == null){
            context.getErrors().add(new BusinessError(ErrorType.UNKNOWN_PADDOCK_TYPE));
        } else {
            context.setConvertedType(type);
        }
       return context;

    }
}
