package doyenm.zooshell;

import doyenm.zooshell.commandLine.commandImpl.zoo.RenameZoo;
import doyenm.zooshell.controller.animalcontroller.*;
import doyenm.zooshell.controller.animalcontroller.evaluation.*;
import doyenm.zooshell.controller.animalcontroller.evaluation.reproduction.*;
import doyenm.zooshell.controller.animalcontroller.evaluation.death.*;
import doyenm.zooshell.controller.animalcontroller.evaluation.wellbeing.*;
import doyenm.zooshell.controller.handymancontroller.HandymanControllersConfig;

import doyenm.zooshell.controller.keepercontroller.KeeperEvaluationAgeingController;
import doyenm.zooshell.controller.keepercontroller.KeeperEvaluationController;
import doyenm.zooshell.controller.keepercontroller.KeeperEvaluationFamilyController;
import doyenm.zooshell.controller.keepercontroller.KeeperEvaluationTaskController;
import doyenm.zooshell.controller.paddockcontroller.PaddockControllerConfig;
import doyenm.zooshell.controller.zoocontroller.EvaluationController;
import doyenm.zooshell.controller.zoocontroller.RenameZooController;
import doyenm.zooshell.controller.zoocontroller.ZooEvaluationController;
import doyenm.zooshell.model.utils.CohabitationFactorHandler;
import doyenm.zooshell.utils.UniformStatistics;
import doyenm.zooshell.validator.AnimalCreationValidator;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 *
 * @author doyenm
 */
@Configuration
@PropertySource("classpath:/doyenm/zooshell/zooshell.properties")
@Import({PaddockControllerConfig.class, HandymanControllersConfig.class})
public class ZooShellZooConfig {
    
    @Autowired
    Environment environment;
    
    @Autowired
    PaddockControllerConfig paddockControllerConfig;
    
    @Autowired
    HandymanControllersConfig handymanControllersConfig;


    @Bean
    AnimalAgeEvaluationController animalAgeEvaluationController() {
        return new AnimalAgeEvaluationController();
    }
    
    @Bean
    AnimalUpdateDyingMeasures animalUpdateDyingMeasures(){
        return new AnimalUpdateDyingMeasures(new AnimalDyingPredicates(),
        Arrays.asList(0.4, 0.3, 0.3, 0.1));
    }

    @Bean
    AnimalDeathPredicates animalDeathPredicates(){
        return new AnimalDeathPredicates(Integer.parseInt(environment.getProperty("animal.turns.agony")));
    }
    
    @Bean
    AnimalDeathEvaluationController animalDeathEvaluationController() {
        return new AnimalDeathEvaluationController(animalUpdateDyingMeasures(), animalDeathPredicates());
    }
    
    @Bean
    FemaleReproductionPredicate femaleReproductionPredicate(){
        return new FemaleReproductionPredicate();
    }
    
      @Bean
    MaleReproductionPredicate maleReproductionPredicate(){
        return new MaleReproductionPredicate();
    }
    
    @Bean
    UniformStatistics uniformStatistics(){
        return new UniformStatistics();
    }
    
    @Bean
    AnimalCreationValidator animalCreationValidator(){
        return new AnimalCreationValidator(Integer.parseInt(environment.getProperty("animal.name.max_length")));
    }
    
    @Bean
    CalvingFunction calvingFunction(){
        return new CalvingFunction(uniformStatistics(), animalCreationValidator());
    }
    
    @Bean
    ExecuteReproductionFunction executeReproductionFunction(){
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
        return new AnimalFoodQuantityEvaluationController(new Utils());
    }

    @Bean
    AnimalFastDaysEvaluationController animalFastDaysEvaluationController() {
        return new AnimalFastDaysEvaluationController();
    }

    @Bean
    AnimalTerritorySizeEvaluationController animalTerritorySizeEvaluationController() {
        return new AnimalTerritorySizeEvaluationController(new Utils());
    }

    @Bean
    AnimalGroupSizeEvaluationController animalGroupSizeEvaluationController() {
        return new AnimalGroupSizeEvaluationController(new Utils());
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
    AnimalEvaluationController animalEvaluationController() {
        return new AnimalEvaluationController(
                animalAgeEvaluationController(),
                new AnimalCohabitationEvaluationController(new CohabitationFactorHandler()),
                animalDeathEvaluationController(),
                animalReproductionEvaluationController(),
                animalWellBeingController());
    }

    @Bean
    KeeperEvaluationAgeingController keeperAgeingController(){
        return new KeeperEvaluationAgeingController();
    }
    @Bean
    KeeperEvaluationTaskController taskController(){
        return new KeeperEvaluationTaskController();
    }
    @Bean
    KeeperEvaluationFamilyController familyController(){
        return new KeeperEvaluationFamilyController();
    }

    @Bean
    KeeperEvaluationController keeperEvaluationController() {
        return new KeeperEvaluationController(keeperAgeingController(),
                taskController(), 
                familyController());
    }
    
    @Bean
    ZooEvaluationController zooEvaluationController(){
        return new ZooEvaluationController();
    } 

    @Bean
    EvaluationController evaluationController() {
        return new EvaluationController(animalEvaluationController(), 
                keeperEvaluationController(), 
                paddockControllerConfig.paddockEvaluationController(), 
                handymanControllersConfig.handymanEvaluationController(),
                zooEvaluationController());
    }
    
    @Bean
    static RenameZooController renameZooController(){
        return new RenameZooController();
    } 
    
    @Bean
    public static RenameZoo renameZoo(){
        return new RenameZoo(renameZooController());
    } 
}
