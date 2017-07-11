package doyenm.zooshell;

import doyenm.zooshell.commandLine.commandImpl.DetailSpecie;
import doyenm.zooshell.commandLine.commandImpl.LsSpecie;
import doyenm.zooshell.commandLine.commandImpl.ls.LsKeeperTask;
import doyenm.zooshell.commandLine.commandImpl.zoo.CreateZoo;
import doyenm.zooshell.commandLine.commandImpl.zoo.DetailZoo;
import doyenm.zooshell.controller.speciecontroller.SpecieDetailsController;
import doyenm.zooshell.controller.zoocontroller.ZooCreationController;
import doyenm.zooshell.controller.zoocontroller.ZooDetailsController;
import doyenm.zooshell.validator.SpecieDetailsValidator;
import doyenm.zooshell.validator.ZooCreationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author doyenm
 */
public class ZooShellCommandConfig {

    // Validators    
    @Autowired
    SpecieDetailsValidator specieDetailsValidator;

    @Autowired
    ZooCreationValidator zooCreationValidator;

    // Controllers
    @Autowired
    SpecieDetailsController specieDetailsController;

    @Autowired
    ZooCreationController zooCreationController;

    @Autowired
    ZooDetailsController zooDetailsController;

    @Bean
    CreateZoo createZoo() {
        return new CreateZoo(zooCreationValidator, zooCreationController);
    }

    @Bean
    DetailZoo detailZoo() {
        return new DetailZoo(zooDetailsController);
    }

    @Bean
    DetailSpecie detailSpecie() {
        return new DetailSpecie(specieDetailsValidator, specieDetailsController);
    }

    @Bean
    LsKeeperTask lsKeeperTask() {
        return new LsKeeperTask();
    }

    @Bean
    LsSpecie lsSpecie() {
        return new LsSpecie();
    }
}
