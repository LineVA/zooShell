package doyenm.zooshell.zoo.creation;

import doyenm.zooshell.zoo.creation.ZooCreationContext;
import doyenm.zooshell.common.name.NameDto;
import doyenm.zooshell.common.name.NameValidator;
import doyenm.zooshell.common.predicates.IntegerValuePredicates;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.function.Predicate;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class ZooCreationValidator implements Predicate<ZooCreationContext> {

    private final IntegerValuePredicates integerValuePredicates;
    private final NameValidator nameValidator;
    
    private final int minWidth;
    private final int minHeight;
    private final int minHorizon;
    private final int minSpeed;
    private final int maxSpeed;

    @Override
    public boolean test(ZooCreationContext t) {
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
        result &= testName(context.getName());
        return result;
    }
    
    private boolean testName(String name){
        NameDto dto = NameDto.builder()
                .testing(name)
                .existingNames(new HashSet<>())
                .build();
        return nameValidator.test(dto);
    }
}