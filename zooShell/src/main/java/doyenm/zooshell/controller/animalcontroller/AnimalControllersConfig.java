package doyenm.zooshell.controller.animalcontroller;

import doyenm.zooshell.controller.animalcontroller.criteria.AnimalsWithDietCriteriaController;
import doyenm.zooshell.controller.animalcontroller.criteria.AnimalsWithPaddockCriteriaController;
import doyenm.zooshell.controller.animalcontroller.criteria.AnimalsWithSexCriteriaController;
import doyenm.zooshell.controller.animalcontroller.criteria.AnimalsWithSpecieCriteriaController;
import doyenm.zooshell.controller.animalcontroller.evaluation.AnimalAgeEvaluationController;
import doyenm.zooshell.controller.animalcontroller.evaluation.AnimalCohabitationEvaluationController;
import doyenm.zooshell.controller.animalcontroller.evaluation.AnimalDeathEvaluationController;
import doyenm.zooshell.controller.animalcontroller.evaluation.AnimalReproductionEvaluationController;
import doyenm.zooshell.controller.animalcontroller.evaluation.AnimalWellBeingController;
import doyenm.zooshell.controller.animalcontroller.evaluation.KeeperUtils;
import doyenm.zooshell.controller.animalcontroller.evaluation.death.AnimalDeathPredicates;
import doyenm.zooshell.controller.animalcontroller.evaluation.death.AnimalDyingPredicates;
import doyenm.zooshell.controller.animalcontroller.evaluation.death.AnimalUpdateDyingMeasures;
import doyenm.zooshell.controller.animalcontroller.evaluation.reproduction.CalvingFunction;
import doyenm.zooshell.controller.animalcontroller.evaluation.reproduction.ExecuteReproductionFunction;
import doyenm.zooshell.controller.animalcontroller.evaluation.reproduction.FemaleReproductionPredicate;
import doyenm.zooshell.controller.animalcontroller.evaluation.reproduction.MaleReproductionPredicate;
import doyenm.zooshell.controller.animalcontroller.evaluation.wellbeing.AnimalBiomeEvaluationController;
import doyenm.zooshell.controller.animalcontroller.evaluation.wellbeing.AnimalDietsEvaluationController;
import doyenm.zooshell.controller.animalcontroller.evaluation.wellbeing.AnimalFastDaysEvaluationController;
import doyenm.zooshell.controller.animalcontroller.evaluation.wellbeing.AnimalFoodQuantityEvaluationController;
import doyenm.zooshell.controller.animalcontroller.evaluation.wellbeing.AnimalGroupSizeEvaluationController;
import doyenm.zooshell.controller.animalcontroller.evaluation.wellbeing.AnimalKeepersTimeInfluenceEvaluationController;
import doyenm.zooshell.controller.animalcontroller.evaluation.wellbeing.AnimalTasksInfluenceEvaluationController;
import doyenm.zooshell.controller.animalcontroller.evaluation.wellbeing.AnimalTerritorySizeEvaluationController;
import doyenm.zooshell.model.utils.CohabitationFactorHandler;
import doyenm.zooshell.utils.UniformStatistics;
import doyenm.zooshell.utils.Utils;
import doyenm.zooshell.validator.AnimalCreationValidator;
import doyenm.zooshell.validator.FindPaddock;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 *
 * @author doyenm
 */
@Configuration
public class AnimalControllersConfig {
    
     @Autowired
    Environment environment;

    @Bean
    Utils utils() {
        return new Utils();
    }
    
     // Controller
    @Bean
    public AnimalChangeNameController animalChangeNameController() {
        return new AnimalChangeNameController();
    }

    @Bean
    public AnimalChangePaddockController animalChangePaddockController() {
        return new AnimalChangePaddockController();
    }

    @Bean
    public AnimalCreationController animalCreationController() {
        return new AnimalCreationController();
    }

    @Bean
    public AnimalDetailsController animalDetailsController() {
        return new AnimalDetailsController(utils());
    }

    @Bean
    public AnimalRemoveController animalRemoveController() {
        return new AnimalRemoveController();
    }

    @Bean
    public AnimalResetDietController animalResetDietController() {
        return new AnimalResetDietController();
    }

    @Bean
    public AnimalUpdateDietController animalUpdateDietController() {
        return new AnimalUpdateDietController();
    }

    @Bean
    public AnimalUpdateContraceptionController animalUpateContraceptionController() {
        return new AnimalUpdateContraceptionController();
    }

    @Bean
    public AnimalUpdateFastDaysController animalUpdateFastDaysController() {
        return new AnimalUpdateFastDaysController();
    }

    @Bean
    public AnimalUpdateFoodQuantityController animalUpdateFoodQuantityController() {
        return new AnimalUpdateFoodQuantityController();
    }

    @Bean
    public AnimalsWithDietCriteriaController animalsWithDietCriteriaController() {
        return new AnimalsWithDietCriteriaController();
    }

    @Bean
    public AnimalsWithSexCriteriaController animalsWithSexCriteriaController() {
        return new AnimalsWithSexCriteriaController();
    }

    @Bean
    public AnimalsWithPaddockCriteriaController animalsWithPaddockCriteriaController() {
        return new AnimalsWithPaddockCriteriaController();
    }

    @Bean
    public AnimalsWithSpecieCriteriaController animalsWithSpecieCriteriaController() {
        return new AnimalsWithSpecieCriteriaController();
    }

    @Bean
    public LsAnimalsWithCriteriaController lsAnimalsWithCriteriaController() {
        return new LsAnimalsWithCriteriaController(
                animalsWithDietCriteriaController(),
                animalsWithSexCriteriaController(),
                animalsWithPaddockCriteriaController(),
                animalsWithSpecieCriteriaController()
        );
    }
    
    @Bean
    AnimalAgeEvaluationController animalAgeEvaluationController() {
        return new AnimalAgeEvaluationController();
    }

    @Bean
    AnimalUpdateDyingMeasures animalUpdateDyingMeasures() {
        return new AnimalUpdateDyingMeasures(new AnimalDyingPredicates(),
                Arrays.asList(0.4, 0.3, 0.3, 0.1));
    }

    @Bean
    AnimalDeathPredicates animalDeathPredicates() {
        return new AnimalDeathPredicates(Integer.parseInt(environment.getProperty("animal.turns.agony")));
    }

    @Bean
    AnimalDeathEvaluationController animalDeathEvaluationController() {
        return new AnimalDeathEvaluationController(animalUpdateDyingMeasures(), animalDeathPredicates());
    }

    @Bean
    FemaleReproductionPredicate femaleReproductionPredicate() {
        return new FemaleReproductionPredicate();
    }

    @Bean
    MaleReproductionPredicate maleReproductionPredicate() {
        return new MaleReproductionPredicate();
    }

    @Bean
    UniformStatistics uniformStatistics() {
        return new UniformStatistics();
    }
    
     @Autowired
    FindPaddock findPaddock;

    @Bean
    AnimalCreationValidator animalCreationValidator() {
        return new AnimalCreationValidator(findPaddock, Integer.parseInt(environment.getProperty("animal.name.max_length")));
    }

    @Bean
    CalvingFunction calvingFunction() {
        return new CalvingFunction(uniformStatistics(), animalCreationValidator());
    }

    @Bean
    ExecuteReproductionFunction executeReproductionFunction() {
        return new ExecuteReproductionFunction(calvingFunction(), uniformStatistics());
    }

    @Bean
    AnimalReproductionEvaluationController animalReproductionEvaluationController() {
        return new AnimalReproductionEvaluationController(
                femaleReproductionPredicate(),
                maleReproductionPredicate(),
                executeReproductionFunction()
        );
    }

    @Bean
    AnimalBiomeEvaluationController animalBiomeEvaluationController() {
        return new AnimalBiomeEvaluationController();
    }

    @Bean
    AnimalDietsEvaluationController animalDietsEvaluationController() {
        return new AnimalDietsEvaluationController();
    }

    @Bean
    AnimalFoodQuantityEvaluationController animalFoodQuantityEvaluationController() {
        return new AnimalFoodQuantityEvaluationController(new doyenm.zooshell.controller.animalcontroller.evaluation.wellbeing.Utils());
    }

    @Bean
    AnimalFastDaysEvaluationController animalFastDaysEvaluationController() {
        return new AnimalFastDaysEvaluationController();
    }

    @Bean
    AnimalTerritorySizeEvaluationController animalTerritorySizeEvaluationController() {
        return new AnimalTerritorySizeEvaluationController(new doyenm.zooshell.controller.animalcontroller.evaluation.wellbeing.Utils());
    }

    @Bean
    AnimalGroupSizeEvaluationController animalGroupSizeEvaluationController() {
        return new AnimalGroupSizeEvaluationController(new doyenm.zooshell.controller.animalcontroller.evaluation.wellbeing.Utils());
    }

    @Bean
    AnimalTasksInfluenceEvaluationController animalTasksInfluenceEvaluationController() {
        return new AnimalTasksInfluenceEvaluationController(new KeeperUtils());
    }

    @Bean
    AnimalKeepersTimeInfluenceEvaluationController animalKeepersTimeInfluenceEvaluationController() {
        return new AnimalKeepersTimeInfluenceEvaluationController(new KeeperUtils());
    }

    @Bean
    AnimalWellBeingController animalWellBeingController() {
        return new AnimalWellBeingController(animalBiomeEvaluationController(),
                animalDietsEvaluationController(),
                animalFoodQuantityEvaluationController(),
                animalFastDaysEvaluationController(),
                animalTerritorySizeEvaluationController(),
                animalGroupSizeEvaluationController(),
                animalTasksInfluenceEvaluationController(),
                animalKeepersTimeInfluenceEvaluationController());
    }

    @Bean
    public AnimalEvaluationController animalEvaluationController() {
        return new AnimalEvaluationController(
                animalAgeEvaluationController(),
                new AnimalCohabitationEvaluationController(new CohabitationFactorHandler()),
                animalDeathEvaluationController(),
                animalReproductionEvaluationController(),
                animalWellBeingController());
    }

}
