package doyenm.zooshell.validator.predicates;

import doyenm.zooshell.context.HandymanCreationContext;
import lombok.RequiredArgsConstructor;

/**
 *s
 * @author doyenm
 */
@RequiredArgsConstructor
public class HandymenNumberPredicate {

    private final double frequencyPerPaddock;
    
    public boolean test(HandymanCreationContext context){
        double ratio = context.getZoo().getPaddocks().size() * frequencyPerPaddock;
        ratio = Math.ceil(ratio);
        return ratio >= (context.getZoo().getHandymen().size() + 1); 
    }
    

}
