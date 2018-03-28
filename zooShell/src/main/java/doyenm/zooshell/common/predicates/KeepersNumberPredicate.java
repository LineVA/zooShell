package doyenm.zooshell.common.predicates;

import doyenm.zooshell.context.KeeperCreationContext;

/**
 *
 * @author doyenm
 */
public class KeepersNumberPredicate {

    public boolean test(KeeperCreationContext context){
        double ratio = context.getPaddocks().size() / 2.0;
        ratio = Math.ceil(ratio);
        // context.getKeepers().size() + 1 : the ones we already have + the new one
        return ratio >= (context.getKeepers().size() + 1); 
    }
    

}
