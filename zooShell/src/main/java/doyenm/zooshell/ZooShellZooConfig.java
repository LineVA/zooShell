package doyenm.zooshell;

import com.sun.imageio.spi.RAFImageInputStreamSpi;
import doyenm.zooshell.commandLine.commandImpl.zoo.RenameZoo;
import doyenm.zooshell.controller.animalcontroller.AnimalEvaluationController;
import doyenm.zooshell.controller.animalcontroller.evaluation.AnimalAgeEvaluationController;
import doyenm.zooshell.controller.animalcontroller.evaluation.AnimalDeathEvaluationController;
import doyenm.zooshell.controller.animalcontroller.evaluation.AnimalReproductionEvaluationController;
import doyenm.zooshell.controller.animalcontroller.evaluation.AnimalWellBeingController;
import doyenm.zooshell.controller.animalcontroller.evaluation.KeeperUtils;
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
import doyenm.zooshell.controller.animalcontroller.evaluation.wellbeing.Utils;
import doyenm.zooshell.controller.keepercontroller.KeeperEvaluationAgeingController;
import doyenm.zooshell.controller.keepercontroller.KeeperEvaluationController;
import doyenm.zooshell.controller.keepercontroller.KeeperEvaluationFamilyController;
import doyenm.zooshell.controller.keepercontroller.KeeperEvaluationTaskController;
import doyenm.zooshell.controller.paddockcontroller.PaddockAgeEvaluationController;
import doyenm.zooshell.controller.paddockcontroller.PaddockEvaluationController;
import doyenm.zooshell.controller.zoocontroller.EvaluationController;
import doyenm.zooshell.controller.zoocontroller.RenameZooController;
import doyenm.zooshell.controller.zoocontroller.ZooEvaluationController;
import doyenm.zooshell.utils.UniformStatistics;
import doyenm.zooshell.validator.AnimalCreationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 *
 * @author doyenm
 */
@Configuration
@PropertySource("classpath:/doyenm/zooshell/zooshell.properties")
public class ZooShellZooConfig {
    
    @Autowired
    Environment environment;


    @Bean
    AnimalAgeEvaluationController animalAgeEvaluationController() {
        return new AnimalAgeEvaluationController();
    }

    @Bean
    AnimalDeathEvaluationController animalDeathEvaluationController() {
        return new AnimalDeathEvaluationController();
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
        return new AnimalEvaluationController(animalAgeEvaluationController(),
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
    PaddockAgeEvaluationController paddockAgeEvaluationController(){
        return new PaddockAgeEvaluationController();
    }
    
    @Bean
    PaddockEvaluationController paddockEvaluationController(){
        return new PaddockEvaluationController(paddockAgeEvaluationController());
    }
    
    @Bean
    ZooEvaluationController zooEvaluationController(){
        return new ZooEvaluationController();
    } 

    @Bean
    EvaluationController evaluationController() {
        return new EvaluationController(animalEvaluationController(), 
                keeperEvaluationController(), 
                paddockEvaluationController(), 
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
