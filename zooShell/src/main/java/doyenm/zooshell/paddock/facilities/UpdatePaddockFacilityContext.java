package doyenm.zooshell.paddock.facilities;

import doyenm.zooshell.errorhandling.BusinessError;
import doyenm.zooshell.model.Paddock;
import doyenm.zooshell.model.PaddockFacility;
import doyenm.zooshell.model.Zoo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author doyenm
 */
@Getter
@Setter
@RequiredArgsConstructor
public class UpdatePaddockFacilityContext {

    private final Zoo zoo;
    private final String paddock;
    private final List<String> arrangements;
    private final boolean isAddingFacilities;

    private Paddock convertedPaddock;
    private List<PaddockFacility> convertedArrangements;

    private List<BusinessError> errors = new ArrayList<>();

    public Set<PaddockFacility> difflists() {
        Set<PaddockFacility> finalFacilities = new HashSet<>();
        finalFacilities.addAll(convertedPaddock.getFacilities());
        if (isAddingFacilities) {
            finalFacilities.addAll(convertedArrangements);
            finalFacilities.remove(PaddockFacility.NONE);
        } else {
            finalFacilities.removeAll(convertedArrangements);
            if(finalFacilities.isEmpty()){
                finalFacilities.add(PaddockFacility.NONE);
            }
        }
        return finalFacilities;
    }

}
