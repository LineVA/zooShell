package doyenm.zooshell.evaluation;

import doyenm.zooshell.animal.AnimalControllersConfig;
import doyenm.zooshell.handyman.HandymanControllersConfig;
import doyenm.zooshell.keeper.KeeperControllersConfig;
import doyenm.zooshell.paddock.PaddockControllerConfig;
import doyenm.zooshell.zoo.ZooControllersConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EvaluationConfig {

    @Autowired
    AnimalControllersConfig animalControllersConfig;

    @Autowired
    PaddockControllerConfig paddockControllerConfig;

    @Autowired
    HandymanControllersConfig handymanControllersConfig;

    @Autowired
    ZooControllersConfig zooControllersConfig;

    @Autowired
    KeeperControllersConfig keeperControllersConfig;

    @Bean
    PenaltiesEvaluationController penaltiesEvaluationController() {
        return new PenaltiesEvaluationController();
    }

    @Bean
    public EvaluationController evaluationController() {
        return new EvaluationController(animalControllersConfig.animalEvaluationController(),
                keeperControllersConfig.keeperEvaluationController(),
                paddockControllerConfig.paddockEvaluationController(),
                handymanControllersConfig.handymanEvaluationController(),
                zooControllersConfig.zooEvaluationController(),
                penaltiesEvaluationController());
    }
}
