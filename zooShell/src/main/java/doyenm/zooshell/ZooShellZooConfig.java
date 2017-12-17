package doyenm.zooshell;

import doyenm.zooshell.commandLine.commandImpl.zoo.RenameZoo;
import doyenm.zooshell.controller.animalcontroller.*;
import doyenm.zooshell.controller.animalcontroller.evaluation.*;
import doyenm.zooshell.controller.animalcontroller.evaluation.reproduction.*;
import doyenm.zooshell.controller.animalcontroller.evaluation.death.*;
import doyenm.zooshell.controller.animalcontroller.evaluation.wellbeing.*;

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
@Import({PaddockControllerConfig.class})
public class ZooShellZooConfig {

    @Autowired
    Environment environment;

    @Autowired
    PaddockControllerConfig paddockControllerConfig;

    @Bean
    public AnimalAgeEvaluationController animalAgeEvaluationController() {
        return new AnimalAgeEvaluationController();
    }

    @Bean
    public AnimalUpdateDyingMeasures animalUpdateDyingMeasures() {
        return new AnimalUpdateDyingMeasures(new AnimalDyingPredicates(),
                Arrays.asList(0.4, 0.3, 0.3, 0.1));
    }

    @Bean
    public AnimalDeathPredicates animalDeathPredicates() {
        return new AnimalDeathPredicates(Integer.parseInt(environment.getProperty("animal.turns.agony")));
    }

    @Bean
    public AnimalDeathEvaluationController animalDeathEvaluationController() {
        return new AnimalDeathEvaluationController(animalUpdateDyingMeasures(), animalDeathPredicates());
    }

    @Bean
    public FemaleReproductionPredicate femaleReproductionPredicate() {
        return new FemaleReproductionPredicate();
    }

    @Bean
    public MaleReproductionPredicate maleReproductionPredicate() {
        return new MaleReproductionPredicate();
    }

    @Bean
    public UniformStatistics uniformStatistics() {
        return new UniformStatistics();
    }

    @Bean
    public AnimalCreationValidator animalCreationValidator() {
        return new AnimalCreationValidator(Integer.parseInt(environment.getProperty("animal.name.max_length")));
    }

    @Bean
    public CalvingFunction calvingFunction() {
        return new CalvingFunction(uniformStatistics(), animalCreationValidator());
    }

    @Bean
    public ExecuteReproductionFunction executeReproductionFunction() {
        return new ExecuteReproductionFunction(calvingFunction(), uniformStatistics());
    }

    @Bean
    public AnimalReproductionEvaluationController animalReproductionEvaluationController() {
        return new AnimalReproductionEvaluationController(
                femaleReproductionPredicate(),
                maleReproductionPredicate(),
                executeReproductionFunction()
        );
    }

    @Bean
    public AnimalBiomeEvaluationController animalBiomeEvaluationController() {
        return new AnimalBiomeEvaluationController();
    }

    @Bean
    public AnimalDietsEvaluationController animalDietsEvaluationController() {
        return new AnimalDietsEvaluationController();
    }

    @Bean
    public AnimalFoodQuantityEvaluationController animalFoodQuantityEvaluationController() {
        return new AnimalFoodQuantityEvaluationController(new Utils());
    }

    @Bean
    public AnimalFastDaysEvaluationController animalFastDaysEvaluationController() {
        return new AnimalFastDaysEvaluationController();
    }

    @Bean
    public AnimalTerritorySizeEvaluationController animalTerritorySizeEvaluationController() {
        return new AnimalTerritorySizeEvaluationController(new Utils());
    }

    @Bean
    public AnimalGroupSizeEvaluationController animalGroupSizeEvaluationController() {
        return new AnimalGroupSizeEvaluationController(new Utils());
    }

    @Bean
    public AnimalTasksInfluenceEvaluationController animalTasksInfluenceEvaluationController() {
        return new AnimalTasksInfluenceEvaluationController(new KeeperUtils());
    }

    @Bean
    public AnimalKeepersTimeInfluenceEvaluationController animalKeepersTimeInfluenceEvaluationController() {
        return new AnimalKeepersTimeInfluenceEvaluationController(new KeeperUtils());
    }

    @Bean
    public AnimalWellBeingController animalWellBeingController() {
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

    @Bean
    public KeeperEvaluationAgeingController keeperAgeingController() {
        return new KeeperEvaluationAgeingController();
    }

    @Bean
    public KeeperEvaluationTaskController taskController() {
        return new KeeperEvaluationTaskController();
    }

    @Bean
    public KeeperEvaluationFamilyController familyController() {
        return new KeeperEvaluationFamilyController();
    }

    @Bean
    public KeeperEvaluationController keeperEvaluationController() {
        return new KeeperEvaluationController(keeperAgeingController(),
                taskController(),
                familyController());
    }

    @Bean
    public ZooEvaluationController zooEvaluationController() {
        return new ZooEvaluationController();
    }

    @Bean
    public EvaluationController evaluationController() {
        return new EvaluationController(animalEvaluationController(),
                keeperEvaluationController(),
                paddockControllerConfig.paddockEvaluationController(),
                zooEvaluationController());
    }

    @Bean
    public RenameZooController renameZooController() {
        return new RenameZooController();
    }

}
