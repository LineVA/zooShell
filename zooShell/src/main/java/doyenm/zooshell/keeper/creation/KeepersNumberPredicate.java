package doyenm.zooshell.keeper.creation;

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
