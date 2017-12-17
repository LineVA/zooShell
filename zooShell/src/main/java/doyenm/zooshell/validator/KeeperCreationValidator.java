package doyenm.zooshell.validator;

import doyenm.zooshell.context.KeeperCreationContext;
import doyenm.zooshell.validator.name.NameDto;
import doyenm.zooshell.validator.name.NameValidator;
import doyenm.zooshell.validator.predicates.KeepersNumberPredicate;
import java.util.function.Predicate;
import lombok.RequiredArgsConstructor;

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
                & nameValidator.test(NameDto.builder()
                        .testing(context.getKeeper())
                        .exitingNames(context.getKeepers().keySet())
                        .build());
    }
    
}
