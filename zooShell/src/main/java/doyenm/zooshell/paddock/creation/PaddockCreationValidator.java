package doyenm.zooshell.paddock.creation;

import doyenm.zooshell.paddock.creation.PaddockCreationContext;
import doyenm.zooshell.common.name.NameDto;
import doyenm.zooshell.common.name.NameValidator;
import doyenm.zooshell.common.predicates.IntegerValuePredicates;
import lombok.RequiredArgsConstructor;

import java.util.Set;
import java.util.function.Predicate;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class PaddockCreationValidator
        implements Predicate<PaddockCreationContext> {

    private final NameValidator nameValidator;
    private final IntegerValuePredicates integerValuePredicates;

    private final int minHeight;
    private final int minWidth;

    @Override
    public boolean test(PaddockCreationContext t) {
        PaddockCreationContext context = t;
        context.convert();
        boolean result;
        result = testName(context.getName(), context.getPaddocks().keySet());
        result &= this.integerValuePredicates.mustBeGreaterOrEqualsThan(context.getConvertedHeight(), minHeight);
        result &= this.integerValuePredicates.mustBeGreaterOrEqualsThan(context.getConvertedWidth(), minWidth);
        return result;
    }
    
    private boolean testName(String name, Set<String> otherNames){
        NameDto dto = NameDto.builder()
                .testing(name)
                .existingNames(otherNames)
                .build();
        return nameValidator.test(dto);
    }
}
