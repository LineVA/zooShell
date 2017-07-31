package doyenm.zooshell;

import doyenm.zooshell.backup.LoadFunction;
import doyenm.zooshell.backup.SaveFunction;
import doyenm.zooshell.commandLine.commandImpl.DetailSpecie;
import doyenm.zooshell.commandLine.commandImpl.Load;
import doyenm.zooshell.commandLine.commandImpl.LsSpecie;
import doyenm.zooshell.commandLine.commandImpl.Save;
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
    
    @Autowired
    SaveFunction saveFunction;
    
    @Autowired
    LoadFunction loadFunction;

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
    
    @Bean
    Save save(){
        return new Save(saveFunction);
    }
    
    @Bean
    Load load(){
        return new Load(loadFunction);
    }
}
