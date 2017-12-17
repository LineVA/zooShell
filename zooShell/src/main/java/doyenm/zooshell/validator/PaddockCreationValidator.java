package doyenm.zooshell.validator;

import doyenm.zooshell.context.PaddockCreationContext;
import doyenm.zooshell.validator.name.NameDto;
import doyenm.zooshell.validator.name.NameValidator;
import doyenm.zooshell.validator.predicates.IntegerValuePredicates;
import java.util.Set;
import java.util.function.Predicate;
import lombok.RequiredArgsConstructor;

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
