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
        boolean result
                = this.integerValuePredicates.mustBeGreaterOrEqualsThan(context.getConvertedWidth(), minWidth);
        result &= this.integerValuePredicates.mustBeGreaterOrEqualsThan(context.getConvertedHeight(), minHeight);
        int min = Math.min(context.getConvertedHeight(), context.getConvertedWidth());
        result &= this.integerValuePredicates.mustBeGreaterOrEqualsThan(context.getConvertedHorizon(), minHorizon);
        result &= this.integerValuePredicates.mustBeLowerOrEqualsThan(context.getConvertedHorizon(), min);
        result &= this.integerValuePredicates.mustBeGreaterOrEqualsThan(context.getConvertedSpeed(), minSpeed);
        result &= this.integerValuePredicates.mustBeLowerOrEqualsThan(context.getConvertedSpeed(), maxSpeed);
        testName(context);
        return context;
    }
    
    private boolean testName(String name){
        NameDto dto = NameDto.builder()
                .testing(name)
                .existingNames(new HashSet<>())
                .build();
        return nameValidator.test(dto);
    }

    private ZooCreationContext testName(ZooCreationContext context){
        NameDto dto = NameDto.builder()
                .testing(context.getName())
                .existingNames(new HashSet<>())
                .build();
        boolean result = nameValidator.test(dto);
        if(result){
            context.getErrors().getErrorsList().add(new BusinessError(ErrorType.INCORRECT_NAME));
        }
        return context;
    }
}
