package doyenm.zooshell.validator;

import doyenm.zooshell.context.PaddockContext;
import doyenm.zooshell.model.Paddock;
import java.util.function.Predicate;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class PaddockValidator implements Predicate<PaddockContext> {

    private final FindPaddock findPaddock;

    @Override
    public boolean test(PaddockContext t) {
        Paddock pad = findPaddock.find(t.getZoo(), t.getPaddock());
       if(pad == null){
           return false;
       } 
       t.setConvertedPaddock(pad);
       return true;
    }

}
