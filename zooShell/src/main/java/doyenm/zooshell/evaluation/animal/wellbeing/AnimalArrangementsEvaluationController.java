package doyenm.zooshell.evaluation.animal.wellbeing;

import doyenm.zooshell.evaluation.AnimalEvaluationContext;
import doyenm.zooshell.model.PaddockArrangement;
import java.util.List;
import java.util.function.Function;

/**
 *
 * @author doyenm
 */
public class AnimalArrangementsEvaluationController 
        implements Function<AnimalEvaluationContext, AnimalEvaluationContext> {

    @Override
    public AnimalEvaluationContext apply(AnimalEvaluationContext t) {
        List<PaddockArrangement> paddockInstallations = t.getArrangements();
        List<PaddockArrangement> specieInstallations = t.getSpecieArrangements();
        if(specieInstallations == null || paddockInstallations == null){
            return t;
        }
        int commonInstallations = 0;
        for(PaddockArrangement installation : paddockInstallations){
            if(specieInstallations.contains(installation)){
                commonInstallations += 1;
            } else {
                commonInstallations -= 1;
            }
        }
        double specieSize = specieInstallations.isEmpty() ? 1.0 : specieInstallations.size();
        t.getWellBeingObj().setInstallationsWellBeing(
                commonInstallations / specieSize * AnimalEvaluationContext.BASE);
        return t;
    }
    
}
