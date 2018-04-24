package doyenm.zooshell.zoo.creation;

import doyenm.zooshell.errorhandling.BusinessError;
import doyenm.zooshell.common.name.NameDto;
import doyenm.zooshell.common.name.NameValidator;
import doyenm.zooshell.common.predicates.IntegerValuePredicates;
import doyenm.zooshell.errorhandling.ErrorType;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.function.Function;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class ZooCreationValidator implements Function<ZooCreationContext, ZooCreationContext> {

    private final IntegerValuePredicates integerValuePredicates;
    private final NameValidator nameValidator;
    
    private final int minWidth;
    private final int minHeight;
    private final int minHorizon;
    private final int minSpeed;
    private final int maxSpeed;

    @Override
    public ZooCreationContext apply(ZooCreationContext t) {
        ZooCreationContext context = t;
        context.convert();
        testDimensions(context);
        testHorizon(context);
        testSpeed(context);
        testName(context);
        return context;
    }

    private void testDimensions(ZooCreationContext context){
        boolean isCorrect
                = this.integerValuePredicates.mustBeGreaterOrEqualsThan(context.getConvertedWidth(), minWidth);
        isCorrect &= this.integerValuePredicates.mustBeGreaterOrEqualsThan(context.getConvertedHeight(), minHeight);
        if(!isCorrect){
            context.getErrors().add(new BusinessError(ErrorType.INCORRECT_DIMENSIONS));
        }
    }

    private void testHorizon(ZooCreationContext context){
        boolean isCorrect = true;
        int max = Math.min(context.getConvertedHeight(), context.getConvertedWidth());
        isCorrect &= this.integerValuePredicates.mustBeGreaterOrEqualsThan(context.getConvertedHorizon(), minHorizon);
        isCorrect &= this.integerValuePredicates.mustBeLowerOrEqualsThan(context.getConvertedHorizon(), max);
        if(!isCorrect){
            context.getErrors().add(new BusinessError(ErrorType.INCORRECT_HORIZON));
        }
    }

    private void testSpeed(ZooCreationContext context){
        boolean isCorrect = true;
        isCorrect &= this.integerValuePredicates.mustBeGreaterOrEqualsThan(context.getConvertedSpeed(), minSpeed);
        isCorrect &= this.integerValuePredicates.mustBeLowerOrEqualsThan(context.getConvertedSpeed(), maxSpeed);
        if(!isCorrect){
            context.getErrors().add(new BusinessError(ErrorType.INCORRECT_HORIZON));
        }
    }


    private ZooCreationContext testName(ZooCreationContext context){
        NameDto dto = NameDto.builder()
                .testing(context.getName())
                .existingNames(new HashSet<>())
                .build();
        boolean result = nameValidator.test(dto);
        if(!result){
            context.getErrors().add(new BusinessError(ErrorType.INCORRECT_NAME));
        }
        return context;
    }
}
