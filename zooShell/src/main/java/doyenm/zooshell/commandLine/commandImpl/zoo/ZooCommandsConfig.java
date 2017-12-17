package doyenm.zooshell.commandLine.commandImpl.zoo;

import doyenm.zooshell.ZooShellControllerConfig;
import doyenm.zooshell.ZooShellValidatorConfig;
import doyenm.zooshell.ZooShellZooConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 *
 * @author doyenm
 */
@Configuration
@Import({ZooShellZooConfig.class, ZooShellValidatorConfig.class, ZooShellControllerConfig.class})
public class ZooCommandsConfig {
    
    @Autowired 
    ZooShellZooConfig zooShellZooConfig;
    
    @Autowired
    ZooShellValidatorConfig zooShellValidatorConfig;
    
    @Autowired
    ZooShellControllerConfig zooShellControllerConfig;
    
    @Bean
    public RenameZoo renameZoo(){
        return new RenameZoo(zooShellZooConfig.renameZooController());
    } 
    
     @Bean
    public CreateZoo createZoo() {
        return new CreateZoo(
                zooShellValidatorConfig.zooCreationValidator(),
                zooShellControllerConfig.zooCreationController());
    }

    @Bean
    public DetailZoo detailZoo() {
        return new DetailZoo(zooShellControllerConfig.zooDetailsController());
    }
    
       
    @Bean
    public Evaluate evaluate(){
        return new Evaluate(zooShellZooConfig.evaluationController());
    }
}
