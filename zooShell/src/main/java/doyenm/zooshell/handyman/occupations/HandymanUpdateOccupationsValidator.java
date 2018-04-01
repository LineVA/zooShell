package doyenm.zooshell.handyman.occupations;

import doyenm.zooshell.handyman.list.LsHandyman;
import doyenm.zooshell.common.FindHandyman;
import doyenm.zooshell.common.FindPaddock;
import lombok.RequiredArgsConstructor;

import java.util.function.Predicate;

/**
 *
 * @author doyenm
 */
@RequiredArgsConstructor
public class HandymanUpdateOccupationsValidator
        implements Predicate<LsHandyman.HandymanUpdateOccupationsContext> {

    private final FindHandyman findHandyman;
    private final FindPaddock findPaddock;
    private final int maxNumberOfAffectations;

    @Override
    public boolean test(LsHandyman.HandymanUpdateOccupationsContext t) {
        t.setHandyman(findHandyman.find(t.getZoo(), t.getHandymanName()));
        t.setPaddock(findPaddock.find(t.getZoo(), t.getPaddockName()));
        return t.getHandyman() != null && t.getPaddock() != null && checkAction(t);
    }
    
    private boolean checkAction(LsHandyman.HandymanUpdateOccupationsContext t){
        if(t.isAddition()){
           return checkAddition(t);
        } else {
            return checkSoustraction(t);
        }
    }
    
    private boolean checkAddition(LsHandyman.HandymanUpdateOccupationsContext t){
        return t.getHandyman().getAffectations().size() < maxNumberOfAffectations 
                    && !t.getHandyman().getAffectations().contains(t.getPaddock());
    }

       private boolean checkSoustraction(LsHandyman.HandymanUpdateOccupationsContext t){
        return t.getHandyman().getAffectations().contains(t.getPaddock());
    }
}
