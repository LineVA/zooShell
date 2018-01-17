package doyenm.zooshell.controller.zoocontroller;

import doyenm.zooshell.utils.Utils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author doyenm
 */
@Configuration
public class ZooControllersConfig {

    @Bean
    Utils utils() {
        return new Utils();
    }

    @Bean
    public ZooDetailsController zooDetailsController() {
        return new ZooDetailsController(utils());
    }

    @Bean
    public ZooCreationController zooCreationController() {
        return new ZooCreationController();
    }
    
     @Bean
    public ZooEvaluationController zooEvaluationController() {
        return new ZooEvaluationController();
    }
    
     @Bean
    public RenameZooController renameZooController() {
        return new RenameZooController();
    }
}
