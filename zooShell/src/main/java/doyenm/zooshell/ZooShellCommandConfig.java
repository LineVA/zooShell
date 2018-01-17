package doyenm.zooshell;

import doyenm.zooshell.backup.LoadFunction;
import doyenm.zooshell.backup.SaveFunction;
import doyenm.zooshell.commandLine.commandImpl.DetailSpecie;
import doyenm.zooshell.commandLine.commandImpl.Load;
import doyenm.zooshell.commandLine.commandImpl.LsSpecie;
import doyenm.zooshell.commandLine.commandImpl.Save;
import doyenm.zooshell.commandLine.commandImpl.ls.LsKeeperTask;
import doyenm.zooshell.commandLine.commandImpl.Evaluate;
import doyenm.zooshell.controller.speciecontroller.SpecieDetailsController;
import doyenm.zooshell.controller.EvaluationController;
import doyenm.zooshell.validator.SpecieDetailsValidator;
import doyenm.zooshell.validator.ZooCreationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

/**
 *
 * @author doyenm
 */
@Import(ZooShellZooConfig.class)
public class ZooShellCommandConfig {

    // Validators    
    @Autowired
    SpecieDetailsValidator specieDetailsValidator;

    @Autowired
    ZooCreationValidator zooCreationValidator;

    // Controllers
    @Autowired
    EvaluationController evaluationController;
    
    @Autowired
    SpecieDetailsController specieDetailsController;

    @Autowired
    SaveFunction saveFunction;
    
    @Autowired
    LoadFunction loadFunction;

    @Bean
    DetailSpecie detailSpecie() {
        return new DetailSpecie(specieDetailsValidator, specieDetailsController);
    }
    
    @Bean
    Evaluate evaluate(){
        return new Evaluate(evaluationController);
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
