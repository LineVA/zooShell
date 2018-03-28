package doyenm.zooshell.validator;

import doyenm.zooshell.context.HandymanCreationContext;
import doyenm.zooshell.common.name.NameDto;
import doyenm.zooshell.common.name.NameValidator;
import doyenm.zooshell.common.predicates.HandymenNumberPredicate;
import lombok.RequiredArgsConstructor;

import java.util.function.Predicate;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class HandymanCreationValidator
        implements Predicate<HandymanCreationContext> {

    private final NameValidator nameValidator;
    private final HandymenNumberPredicate handymenNumberPredicate;

    @Override
    public boolean test(HandymanCreationContext t) {
        return nameValidator.test(NameDto.builder()
                .testing(t.getName())
                .existingNames(t.getHandymenSet())
                .build())
                && handymenNumberPredicate.test(t);
    }

}
