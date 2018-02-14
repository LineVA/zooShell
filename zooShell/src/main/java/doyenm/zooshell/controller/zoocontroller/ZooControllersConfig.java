package doyenm.zooshell.controller.zoocontroller;

import doyenm.zooshell.controller.eventhandling.zoo.KeeperTrainingHandler;
import doyenm.zooshell.controller.eventhandling.zoo.ZooEventsHandler;
import doyenm.zooshell.utils.Utils;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 *
 * @author doyenm
 */
@Configuration
public class ZooControllersConfig {

    @Autowired
    Environment environment;

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
        List<ZooEventsHandler> handlers = Arrays.asList(new KeeperTrainingHandler(
                environment.getProperty("keeper.training.training_per_x_turns", Integer.class)
        ));
        return new ZooEvaluationController(handlers);
    }

    @Bean
    public RenameZooController renameZooController() {
        return new RenameZooController();
    }
}
