package doyenm.zooshell.evaluation.animal.wellbeing;

import doyenm.zooshell.evaluation.AnimalEvaluationContext;
import doyenm.zooshell.model.PaddockFacility;
import java.util.List;
import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class AnimalFacilitiesEvaluationController
        implements Function<AnimalEvaluationContext, AnimalEvaluationContext> {

    @Override
    public AnimalEvaluationContext apply(AnimalEvaluationContext t) {
        List<PaddockFacility> paddockFacilities = t.getFacilities();
        List<PaddockFacility> specieFacilities = t.getSpecieFacilities();
        if(specieFacilities == null || paddockFacilities == null){
            return t;
        }
        int commonInstallations = 0;
        for(PaddockFacility installation : paddockFacilities){
            if(specieFacilities.contains(installation)){
                commonInstallations += 1;
            } else {
                commonInstallations -= 1;
            }
        }
        double specieSize = specieFacilities.isEmpty() ? 1.0 : specieFacilities.size();
        t.getWellBeingObj().setInstallationsWellBeing(
                commonInstallations / specieSize * AnimalEvaluationContext.BASE);
        return t;
    }
    
}
