package doyenm.zooshell.controller.zoocontroller;

import doyenm.zooshell.controller.eventhandling.zoo.KeeperTrainingHandler;
import doyenm.zooshell.controller.eventhandling.zoo.ZooEventsHandler;
import doyenm.zooshell.utils.Utils;
import java.util.Arrays;
import java.util.List;
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

    private final List<ZooEventsHandler> handlers = Arrays.asList(new KeeperTrainingHandler());

    @Bean
    public ZooEvaluationController zooEvaluationController() {
        return new ZooEvaluationController(handlers);
    }

    @Bean
    public RenameZooController renameZooController() {
        return new RenameZooController();
    }
}
