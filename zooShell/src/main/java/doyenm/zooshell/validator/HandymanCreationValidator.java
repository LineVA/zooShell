package doyenm.zooshell.validator;

import doyenm.zooshell.context.HandymanCreationContext;
import doyenm.zooshell.validator.name.NameDto;
import doyenm.zooshell.validator.name.NameValidator;
import java.util.function.Predicate;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class HandymanCreationValidator
        implements Predicate<HandymanCreationContext> {
    
    private final NameValidator nameValidator;
    
    @Override
    public boolean test(HandymanCreationContext t) {
        return nameValidator.test(NameDto.builder()
                .testing(t.getName())
                .existingNames(t.getHandymenSet())
                .build());
    }
    
}
