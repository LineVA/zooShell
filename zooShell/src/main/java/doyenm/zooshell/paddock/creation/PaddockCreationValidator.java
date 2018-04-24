package doyenm.zooshell.paddock.creation;

import doyenm.zooshell.common.name.NameDto;
import doyenm.zooshell.common.name.NameValidator;
import doyenm.zooshell.common.predicates.IntegerValuePredicates;
import doyenm.zooshell.errorhandling.BusinessError;
import doyenm.zooshell.errorhandling.ErrorType;
import lombok.RequiredArgsConstructor;

import java.util.function.Function;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class PaddockCreationValidator
        implements Function<PaddockCreationContext, PaddockCreationContext> {

    private final NameValidator nameValidator;
    private final IntegerValuePredicates integerValuePredicates;

    private final int minHeight;
    private final int minWidth;

    @Override
    public PaddockCreationContext apply(PaddockCreationContext t) {
        PaddockCreationContext context = t;
        context.convert();
        testName(context);
        testDimensions(context);
        return context;
    }

    private void testDimensions(PaddockCreationContext context){
        boolean isCorrect
                = this.integerValuePredicates.mustBeGreaterOrEqualsThan(context.getConvertedWidth(), minWidth);
        isCorrect &= this.integerValuePredicates.mustBeGreaterOrEqualsThan(context.getConvertedHeight(), minHeight);
        if(!isCorrect){
            context.getErrors().add(new BusinessError(ErrorType.INCORRECT_DIMENSIONS));
        }
    }
    
    private PaddockCreationContext testName(PaddockCreationContext context){
        NameDto dto = NameDto.builder()
                .testing(context.getName())
                .existingNames(context.getPaddocksNameSet())
                .build();
        boolean result = nameValidator.test(dto);
        if(!result){
            context.getErrors().add(new BusinessError(ErrorType.INCORRECT_NAME));
        }
        return context;
    }
}
