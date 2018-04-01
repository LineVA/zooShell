package doyenm.zooshell.keeper.creation;

import doyenm.zooshell.common.name.NameDto;
import doyenm.zooshell.common.name.NameValidator;
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
