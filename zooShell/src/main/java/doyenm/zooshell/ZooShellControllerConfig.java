package doyenm.zooshell;

import doyenm.zooshell.controller.speciecontroller.SpecieDetailsController;
import doyenm.zooshell.controller.zoocontroller.ZooCreationController;
import doyenm.zooshell.controller.zoocontroller.ZooDetailsController;
import doyenm.zooshell.utils.Utils;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author doyenm
 */
public class ZooShellControllerConfig {

    @Bean
    Utils utils() {
        return new Utils();
    }

    @Bean
    SpecieDetailsController specieDetailsController() {
        return new SpecieDetailsController();
    }

    @Bean
    ZooCreationController zooCreationController() {
        return new ZooCreationController();
    }

    @Bean
    ZooDetailsController zooDetailsController() {
        return new ZooDetailsController(utils());
    }

}
