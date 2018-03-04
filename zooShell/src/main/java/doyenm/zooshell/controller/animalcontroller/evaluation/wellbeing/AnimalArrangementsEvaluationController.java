package doyenm.zooshell.controller.animalcontroller.evaluation.wellbeing;

import doyenm.zooshell.context.AnimalEvaluationContext;
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
        List<PaddockArrangement> paddockInstallations = t.getPaddock().getArrangements();
        List<PaddockArrangement> specieInstallations = t.getSpecie().getArrangements().getArrangements();
        int commonInstallations = 0;
        for(PaddockArrangement installation : paddockInstallations){
            if(specieInstallations.contains(installation)){
                commonInstallations += 1;
            } else {
                commonInstallations -= 1;
            }
        }
        t.setInstallationsWellBeing(commonInstallations / specieInstallations.size() * t.getBase());
        return t;
    }
    
}
