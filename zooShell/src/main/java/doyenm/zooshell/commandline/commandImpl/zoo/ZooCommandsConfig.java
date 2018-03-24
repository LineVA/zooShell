package doyenm.zooshell.commandline.commandImpl.zoo;

import doyenm.zooshell.validator.ZooValidatorsConfig;
import doyenm.zooshell.controller.zoocontroller.ZooControllersConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 *
 * @author doyenm
 */
@Configuration
@Import({ZooValidatorsConfig.class, ZooControllersConfig.class})
public class ZooCommandsConfig {

    @Autowired
    ZooValidatorsConfig validators;

    @Autowired
    ZooControllersConfig controllers;

    @Bean
    public CreateZoo createZoo() {
        return new CreateZoo(
                validators.zooCreationValidator(),
                controllers.zooCreationController()
        );
    }
    
    @Bean
    public DetailZoo detailZoo() {
        return new DetailZoo(
                controllers.zooDetailsController()
        );
    }


    @Bean
    public RenameZoo renameZoo() {
        return new RenameZoo(controllers.renameZooController());
    }
}
