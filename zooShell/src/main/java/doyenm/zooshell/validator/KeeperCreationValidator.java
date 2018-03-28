package doyenm.zooshell.validator;

import doyenm.zooshell.context.KeeperCreationContext;
import doyenm.zooshell.common.name.NameDto;
import doyenm.zooshell.common.name.NameValidator;
import doyenm.zooshell.common.predicates.KeepersNumberPredicate;
import lombok.RequiredArgsConstructor;

import java.util.function.Predicate;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class KeeperCreationValidator implements Predicate<KeeperCreationContext> {
    
    private final NameValidator nameValidator;
    private final KeepersNumberPredicate keepersNumberPredicate;
    
    @Override
    public boolean test(KeeperCreationContext t) {
        KeeperCreationContext context = t;
        
        return this.keepersNumberPredicate.test(context)
                && nameValidator.test(NameDto.builder()
                        .testing(context.getKeeper())
                        .existingNames(context.getKeepers().keySet())
                        .build());
    }
    
}
