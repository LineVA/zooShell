package doyenm.zooshell.zoo;

import doyenm.zooshell.evaluation.eventhandling.zoo.KeeperTrainingHandler;
import doyenm.zooshell.evaluation.eventhandling.zoo.ZooEventsHandler;
import doyenm.zooshell.zoo.rename.RenameZooController;
import doyenm.zooshell.evaluation.ZooEvaluationController;
import doyenm.zooshell.utils.Utils;
import doyenm.zooshell.zoo.creation.ZooCreationController;
import doyenm.zooshell.zoo.details.ZooDetailsController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Arrays;
import java.util.List;

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
