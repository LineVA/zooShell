package doyenm.zooshell.paddock.rename;

import doyenm.zooshell.errorhandling.BusinessError;
import doyenm.zooshell.errorhandling.ErrorType;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.common.name.NameDto;
import doyenm.zooshell.common.name.NameValidator;
import doyenm.zooshell.common.FindPaddock;
import doyenm.zooshell.paddock.creation.PaddockCreationContext;
import lombok.RequiredArgsConstructor;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class PaddockChangeNameValidator
        implements Function<PaddockChangeNameContext, PaddockChangeNameContext> {

    private final FindPaddock findPaddock;
    private final NameValidator nameValidator;

    @Override
    public PaddockChangeNameContext apply(PaddockChangeNameContext t) {
        PaddockChangeNameContext context = t;
        Paddock pad = findPaddock.find(context.getZoo(), context.getCurrentName());
        if (pad == null) {
            context.getErrors().add(new BusinessError(ErrorType.UNKNOWN_PADDOCK));
        }
        context.setConvertedPaddock(pad);
        testName(context);
        return context;
    }


    private PaddockChangeNameContext testName(PaddockChangeNameContext context){
        NameDto dto = NameDto.builder()
                .testing(context.getNewName())
                .existingNames(context.getPaddocks())
                .build();
        boolean result = nameValidator.test(dto);
        if(!result){
            context.getErrors().add(new BusinessError(ErrorType.INCORRECT_NAME));
        }
        return context;
    }
}
