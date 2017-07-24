package doyenm.zooshell.validator;

import doyenm.zooshell.context.KeeperCreationContext;
import doyenm.zooshell.validator.predicates.KeepersNumberPredicate;
import doyenm.zooshell.validator.predicates.StringLengthPredicates;
import doyenm.zooshell.validator.predicates.UniquenessNamesBiPredicates;
import java.util.function.Predicate;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class KeeperCreationValidator implements Predicate<KeeperCreationContext> {

    private final StringLengthPredicates stringLengthPredicates;
    private final UniquenessNamesBiPredicates uniquenessNamesBiPredicates;
    private final KeepersNumberPredicate keepersNumberPredicate;
    
    @Override
    public boolean test(KeeperCreationContext t) {
        KeeperCreationContext context = t;
        
        return 
                this.keepersNumberPredicate.test(context)
                & this.stringLengthPredicates.mustBeLowerOrEqualsThan(context.getKeeper(), 50)
                & this.uniquenessNamesBiPredicates.test(context.getKeeper().toUpperCase(), context.getKeepers().keySet());
    }

}
