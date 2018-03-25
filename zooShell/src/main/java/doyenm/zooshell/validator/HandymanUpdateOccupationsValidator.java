package doyenm.zooshell.validator;

import doyenm.zooshell.context.HandymanUpdateOccupationsContext;
import lombok.RequiredArgsConstructor;

import java.util.function.Predicate;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class HandymanUpdateOccupationsValidator
        implements Predicate<HandymanUpdateOccupationsContext> {

    private final FindHandyman findHandyman;
    private final FindPaddock findPaddock;
    private final int maxNumberOfAffectations;

    @Override
    public boolean test(HandymanUpdateOccupationsContext t) {
        t.setHandyman(findHandyman.find(t.getZoo(), t.getHandymanName()));
        t.setPaddock(findPaddock.find(t.getZoo(), t.getPaddockName()));
        return t.getHandyman() != null && t.getPaddock() != null && checkAction(t);
    }
    
    private boolean checkAction(HandymanUpdateOccupationsContext t){
        if(t.isAddition()){
           return checkAddition(t);
        } else {
            return checkSoustraction(t);
        }
    }
    
    private boolean checkAddition(HandymanUpdateOccupationsContext t){
        return t.getHandyman().getAffectations().size() < maxNumberOfAffectations 
                    && !t.getHandyman().getAffectations().contains(t.getPaddock());
    }

       private boolean checkSoustraction(HandymanUpdateOccupationsContext t){
        return t.getHandyman().getAffectations().contains(t.getPaddock());
    }
}
