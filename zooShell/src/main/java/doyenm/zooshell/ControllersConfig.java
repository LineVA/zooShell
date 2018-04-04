package doyenm.zooshell;

import doyenm.zooshell.animal.AnimalControllersConfig;
import doyenm.zooshell.evaluation.EvaluationController;
import doyenm.zooshell.handyman.HandymanControllersConfig;
import doyenm.zooshell.keeper.KeeperControllersConfig;
import doyenm.zooshell.paddock.PaddockControllerConfig;
import doyenm.zooshell.evaluation.PenaltiesEvaluationController;
import doyenm.zooshell.zoo.ZooControllersConfig;
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
