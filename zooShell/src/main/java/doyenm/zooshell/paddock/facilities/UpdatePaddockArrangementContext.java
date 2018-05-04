package doyenm.zooshell.paddock.facilities;

import doyenm.zooshell.errorhandling.BusinessError;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.PaddockArrangement;
import doyenm.zooshell.model.Zoo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author doyenm
 */
@Getter
@Setter
@RequiredArgsConstructor
public class UpdatePaddockArrangementContext {

    private final Zoo zoo;
    private final String paddock;
    private final List<String> arrangements;
    private final boolean isAddingFacilities;

    private Paddock convertedPaddock;
    private List<PaddockArrangement> convertedArrangements;

    private List<BusinessError> errors = new ArrayList<>();

    public Set<PaddockArrangement> difflists(){
        Set<PaddockArrangement> finalFacilities = new HashSet<>();
        finalFacilities.addAll(convertedPaddock.getArrangements());
        finalFacilities.addAll(convertedArrangements);
        finalFacilities.remove(PaddockArrangement.NONE);
        return finalFacilities;
    }

}
