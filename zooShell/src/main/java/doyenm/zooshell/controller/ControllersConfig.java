package doyenm.zooshell.controller;

import doyenm.zooshell.controller.animalcontroller.AnimalControllersConfig;
import doyenm.zooshell.controller.handymancontroller.HandymanControllersConfig;
import doyenm.zooshell.controller.keepercontroller.KeeperControllersConfig;
import doyenm.zooshell.controller.paddockcontroller.PaddockControllerConfig;
import doyenm.zooshell.controller.zoocontroller.ZooControllersConfig;
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
@Import({PaddockControllerConfig.class, HandymanControllersConfig.class, ZooControllersConfig.class, KeeperControllersConfig.class})
public class ControllersConfig {
    
    @Autowired
    Environment environment;
    
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