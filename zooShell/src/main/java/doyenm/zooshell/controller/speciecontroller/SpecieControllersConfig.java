package doyenm.zooshell.controller.speciecontroller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author doyenm
 */
@Configuration
public class SpecieControllersConfig {
    
    @Bean
    public SpecieDetailsController specieDetailsController(){
        return new SpecieDetailsController();
    }
}
